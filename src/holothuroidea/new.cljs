(ns holothuroidea.new
  (:require [holothuroidea.util :as util]
            [cljs.nodejs :as node]
            [cljs.core.async]
            [clojure.string :as string]))


(def colors (node/require "colors"))
(def fs (node/require "fs"))
(def npath (node/require "path"))

(defn print-input-tip []
  (println (.bold/yellow colors "✖ Invaild input\nholothuroidea config <set/read> <key> [value]")))


(def new-template [args]

  ())
