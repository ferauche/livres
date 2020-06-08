<template>
  <nav class="main-menu">
    <ul>
      <li v-for="item in items" :key="item.id">
        <router-link v-bind:to="item.href">
          <i v-bind:class="item.icon"></i>
          <span class="nav-text">{{ item.name }}</span>
        </router-link>
      </li>
    </ul>
  </nav>
</template>

<script>
export default {
  data: function() {
    return {
      items: [
        {
          icon: "fa fa-hashtag fa-2x",
          name: "Home",
          href: "/"
        },
        {
          icon: "fa fa-hashtag fa-2x",
          name: "Cad Precomunidade",
          href: "/cadprecomunidade"
        },
        {
          icon: "fa fa-hashtag fa-2x",
          name: "Consumidores",
          href: "/consumidores"
        },
        {
          icon: "fa fa-hashtag fa-2x",
          name: "Produtos",
          href: "/produtos"
        },
        {
          icon: "fa fa-hashtag fa-2x",
          name: "Checkout",
          href: "/checkout"
        }
      ]
    };
  }
};
</script>
