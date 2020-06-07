<template>
  <div>
    <com-side-bar></com-side-bar>
    <com-menu></com-menu>
    <div class="container pt-5 pt-80">
      <router-view></router-view>
    </div>
  </div>
</template>

<script>
import ComSideBar from "./components/ComSideBar";
import ComMenu from "./components/ComMenu";

export default {
  components: {
    "com-side-bar": ComSideBar,
    "com-menu": ComMenu,
  },
};
</script>