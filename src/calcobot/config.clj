(ns calcobot.config
  (:require [clojure.edn :as edn])
  (:gen-class))

(def ^:private config-path "resources/config.edn")

(defn ^:private read-config []
     (slurp config-path))

(defn ^:private get-token-from [content]
  (-> content
      (edn/read-string)
      (get :token)))

(defn get-token []
  (get-token-from (read-config)))

(defn ^:private get-url-from [content]
  (-> content
      (edn/read-string)
      (get :url)))

(defn get-url []
  (get-url-from (read-config)))
