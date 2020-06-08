import Vue from "vue";
import VueRouter from "vue-router";
import App from "./App.vue";
import Routes from "./routes";
import Toaster from 'v-toaster';

import 'v-toaster/dist/v-toaster.css';

Vue.use(VueRouter);
Vue.use(Toaster, {timeout: 5000});

const router = new VueRouter({
  routes: Routes,
  mode: "history",
});

Vue.config.productionTip = false;

new Vue({
  router: router,
  render: (h) => h(App),
}).$mount("#app");
