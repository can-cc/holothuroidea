(ns test.build
  (:require [cljs.test :refer-macros [deftest is testing run-tests]]
            [cljs.nodejs :as nodejs]
            [holothuroidea.build :as build]))

(nodejs/enable-util-print!)

(deftest test-test
  (is (= "test2" (build/test))))

;; (cljs.test/run-tests)


