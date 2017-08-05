(ns test.build
  (:require [cljs.test :refer-macros [deftest is testing run-tests]]
            [cljs.nodejs :as nodejs]
            [holothuroidea.build :as build]))

(nodejs/enable-util-print!)

(deftest parse-path
  (is (=
       '({:name "husky", :path "test/mock/source/husky", :articles ("<p>&ndash;title:Date &ndash;date:2016-03-22 16:21:14 &ndash;tag:<h1>&mdash;</h1> winder is coming</p>")})
       (build/parse-path "test/mock"))))
