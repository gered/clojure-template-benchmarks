(defproject clojure-template-benchmarks "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :main clojure-template-benchmarks.core
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [clabango "0.5"]
                 [criterium "0.4.3"] ; 0.3.1
                 [enlive "1.1.5"]
                 [hiccup "1.0.5"]
                 [stencil "0.3.5"]
                 [selmer "0.7.2"]
                 [tinsel "0.4.0" :exclusions [hickory]]
                 [me.raynes/laser "2.0.0-SNAPSHOT"]
                 [me.shenfeng/mustache "1.1"]
                 [clj-pebble "0.1.0-SNAPSHOT"]
                 [clojure-csv/clojure-csv "2.0.1"]])
