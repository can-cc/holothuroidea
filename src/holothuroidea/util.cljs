(ns holothuroidea.util
  (:require [cljs.nodejs :as node]
            [cljs.core.async]
            [promesa.core :as p]
            [promesa.async-cljs :refer-macros [async]]
            ))

(def fs (node/require "fs"))

(defn read-dir [path]
  (js->clj (.readdirSync fs path)))

(defn read-file [path]
  (.readFileSync fs path ))

(defn file? [path]
  (-> (.lstatSync fs path)
      (.isFile)))

(defn dir? [path]
  (-> (.lstatSync fs path)
      (.isDirectory)))
