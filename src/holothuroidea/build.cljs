(ns holothuroidea.build
  (:require [holothuroidea.util :as util]
            [cljs.nodejs :as node]
            [cljs.core.async]
            [promesa.core :as p]
            [promesa.async-cljs :refer-macros [async]]
            [fipp.edn :refer (pprint) :rename {pprint fipp}]
            [markdown.core :refer [md->html]]
            ))

(def fs (node/require "fs"))
(def npath (node/require "path"))

(defn write-category-data [output category-data]
  (util/write-file! (.join npath output (str (:name category-data) ".json"))
                    (:articles category-data)))

(defn check-output-and-mkdir! [output]
  (when (not (util/exist? output))
    (util/mkdir! output)))

;; EXP map 里面不能 println

(defn parse-md-file [path]
  (md->html (util/read-file path))) 

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
             :articles (parse-path-all-articles (.join npath source-path name))
             })))))

(defn build-tree! [rest]
  (let [input (first rest)
        output (second rest)
        parsed (parse-path input)]
    (check-output-and-mkdir! output)
    (->> parsed
         (mapv #(write-category-data output %)))))
