import axios from "axios";

export const http = axios.create({
  baseURL: "http://35.188.130.149:8080/",
});
