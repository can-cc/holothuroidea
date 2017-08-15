(ns holothuroidea.config
  (:require [holothuroidea.util :as util]
            [cljs.nodejs :as node]
            [cljs.core.async]
            [clojure.string :as string]))

(def colors (node/require "colors"))
(def fs (node/require "fs"))
(def npath (node/require "path"))

(def config-file-path (.join npath node/process.env.HOME ".holothuroidea.json"))

(defn print-input-tip []
  (println (.bold/yellow colors "✖ Invaild input\nholothuroidea config <set/read> <key> [value]")))

(defn read-config-var [args]
  (let [key (first args)]
    (if (and (config-file-exist) (get (get-config-file) (keyword key)))
      (println (.bold/green colors (get (get-config-file) (keyword key))))
      (println (.bold/yellow colors (str key " is empty."))))))

(defn config-file-exist []
  (util/exist? config-file-path))

(defn get-config-file []
  (js->clj (->> (util/read-file config-file-path)
                (.parse js/JSON)) :keywordize-keys true))

(defn write-config! [data]
  (util/write-file! config-file-path (.stringify js/JSON (clj->js data) nil 2)))

(defn set-config-var! [args]
  (if (< (count args) 2)
    (print-input-tip)
    (let [key (first args)
          value (second args)
          kv-map {(keyword key) value}]
      (if (config-file-exist)
        (let [config (get-config-file)]
          (write-config! (into (sorted-map) [config kv-map]))) ;; TODO do not dry
        (write-config! kv-map)))))

(defn config [args]
  (if (< (count args) 2)
    (print-input-tip)
    (let [config-command (first args)]
      (cond
        (= config-command "set") (set-config-var! (rest args))
        (= config-command "read") (read-config-var (rest args))
        :else (print-input-tip)))))
