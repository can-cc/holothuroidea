(ns holothuroidea.build
  (:require [holothuroidea.util :as util]
            [cljs.nodejs :as node]
            [cljs.core.async]
            [promesa.core :as p]
            [promesa.async-cljs :refer-macros [async]]
            [fipp.edn :refer (pprint) :rename {pprint fipp}]
            ))

(def fs (node/require "fs"))
(def npath (node/require "path"))


;; EXP map 里面不能 println
(defn build-tree [rest]
  (let [path (first rest)]
    (build-tree-path path)
    )
  )

(defn list-files [path]
  (-> (util/read-dir path)
      (p/then println)))

(defn build-tree-path [path]
  (let [source-path (.join npath path "source")]
    (->>
     (util/read-dir source-path)
     (filter #(util/dir? (.join npath source-path %)))
     
     
        ))
  )

