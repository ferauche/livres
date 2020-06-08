<template>
  <nav class="navbar navbar-expand-sm navbar-dark bg-dark">
    <a class="navbar-brand" href="#">Livres Baixada Santista</a>
    <button
      class="navbar-toggler btn"
      type="button"
      data-toggle="collapse"
      data-target="#navbarSupportedContent"
      aria-controls="navbarSupportedContent"
      aria-expanded="false"
      aria-label="Toggle navigation"
    >
      <span class="navbar-toggler-icon"></span>
    </button>

    <div
      class="navbar-collapse nav-collapse collapse"
      id="navbarSupportedContent"
    >
      <ul class="navbar-nav mr-auto">
        <li class="nav-item">
          <router-link to="/" class="nav-link">Lista</router-link>
        </li>
        <li class="nav-item">
          <router-link to="/form" class="nav-link">Form</router-link>
        </li>
        <li class="nav-item">
          <a href="#" class="nav-link">Link 3</a>
        </li>
      </ul>
    </div>
  </nav>
</template>
