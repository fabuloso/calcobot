(ns calcobot.telegram-api
(:require [clojure.walk :refer [keywordize-keys]]
          [cheshire.core :refer [parse-string]])
  (:gen-class))

(defn get-chat-id [update-response]
  (-> update-response
      (first)
      (get :message)
      (get :chat)
      (get :id)))

(defn get-message [update-response]
  (-> update-response
      (last)
      (get :message)
      (get :text)))

(defn get-offset [update-response]
  (-> update-response
      (last)
      (get :message)
      (get :update_id)))

(defn get-result-from-response [response-string]
  (-> response-string
      parse-string
      keywordize-keys
      (#(if (:ok %) (:result %) nil))))

(defn parse-update [response]
  (get-result-from-response
   (get-in response [:body])))
  
