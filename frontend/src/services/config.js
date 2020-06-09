import axios from "axios";

export const http = axios.create({
  // PRODUÇÃO
  // baseURL: "http://livresbs.ga:8080/",
  // DESENVOLVIMENTO
  baseURL: "http://localhost:8080/",
});
