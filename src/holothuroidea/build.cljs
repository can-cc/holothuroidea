(ns holothuroidea.build
  (:require [holothuroidea.util :as util]
            [cljs.nodejs :as node]
            [cljs.core.async]
            [promesa.core :as p]
            [promesa.async-cljs :refer-macros [async]]
            [fipp.edn :refer (pprint) :rename {pprint fipp}]
            [markdown.core :refer [md->html]]))

(def fs (node/require "fs"))
(def npath (node/require "path"))
(def ora (node/require "ora"))
(def moment (node/require "moment"))
(def parseFormat (node/require "moment-parseformat"))

(defn write-category-data! [output category-data]
  (util/write-file! (.join npath output (str (:name category-data) ".json"))
                    (.stringify js/JSON (clj->js category-data))))

(defn write-summary-data! [output summary-data]
  (util/write-file! (.join npath output "_summary.json")
                    (.stringify js/JSON (clj->js summary-data))))

(defn check-output-and-mkdir! [output]
  (when (not (util/exist? output))
    (util/mkdir! output)))

(defn parse-meta [meta]
  (let [lines (clojure.string/split-lines meta)]
    (->> lines
         (map #(clojure.string/split % #":" 2))
         (map (fn [kv]
                (let [key (subs (first kv) "2")
                      value (if (= key "date")
                              (.valueOf (moment (second kv) (parseFormat (second kv))) "dddd")
                              (second kv))]
                  [(keyword key) value])
                ))
         (into (sorted-map)))))

(defn parse-md-file [path]
  (let [raw (util/read-file path)
        meta-and-content (clojure.string/split raw "###")
        meta (first meta-and-content)
        content (second meta-and-content)]
    (when (clojure.string/blank? content) (println "Warning: " path "not content"))
    (into (sorted-map) [{:content (md->html content)} (parse-meta meta)])))

(defn parse-path-all-articles [path]
  (->> (util/read-dir path)
       (map #(parse-md-file (.join npath path %)))))

(defn parse-path [path]
  (let [source-path (.join npath path "source")]
    (->>
     (util/read-dir source-path)
     (filter #(util/dir? (.join npath source-path %)))
     (map (fn [name]
            {:name name
             :path (.join npath source-path name)
             :articles (parse-path-all-articles (.join npath source-path name))})))))

(defn summarify [parsed]
  (map (fn [category]
         {:name (:name category)
          :count (count category)})
       parsed))

(defn build! [rest]
  (let [spinner (.start (ora "parseing...."))
        input (first rest)
        output (second rest)
        parsed (parse-path input)
        summary (summarify parsed)]
    (check-output-and-mkdir! output)
    (mapv #(write-category-data! output %) parsed)
    (write-summary-data! output summary)
    (.succeed spinner "build succeed!")))
