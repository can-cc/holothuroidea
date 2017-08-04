(ns holothuroidea.build
  (:require [holothuroidea.util :as util]
            [cljs.nodejs :as node]
            [cljs.core.async]
            [promesa.core :as p]
            [promesa.async-cljs :refer-macros [async]]
            ))

(def fs (node/require "fs"))

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
  (-> (util/read-dir path)
      (p/then (partial filter util/dir? (partial str path)))
      (p/then (partial mapv println))
      )
  )
