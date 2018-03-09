(ns test.build
  (:require [cljs.test :refer-macros [deftest is testing run-tests]]
            [cljs.nodejs :as nodejs]
            [holothuroidea.build :as build]))

(nodejs/enable-util-print!)

(deftest parse-path
  (is (=
       '({:name "husky",
          :path "test/mock/source/husky",
          :articles ({:content "<p>winder is coming</p>",
                      :date 1458634874000, :tag "", :title "First"})})
       (build/parse-path "test/mock"))))
