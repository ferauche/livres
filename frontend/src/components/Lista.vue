<template>
  <table class="container table col-lg-10">
    <thead class="thead">
      <tr>
        <th scope="col">Id</th>
        <th scope="col">Name</th>
        <th scope="col">Username</th>
        <th scope="col">Email</th>
      </tr>
    </thead>

    <tbody>
      <tr v-for="user in users" :key="user.id">
        <th scope="row">{{ user.id }}</th>
        <td>{{ user.name }}</td>
        <td>{{ user.username }}</td>
        <td>{{ user.email }}</td>
      </tr>
    </tbody>
  </table>
</template>

<script>
import Users from "../services/users";

export default {
  data() {
    return {
      users: []
    };
  },
  mounted() {
    Users.listar().then(result => {
      console.log(result.data);
      this.users = result.data;
    });
  }
};
</script>