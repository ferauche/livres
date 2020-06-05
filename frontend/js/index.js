const Home = { template: '<p>Página Inicial</p>' }
const NotFound = { template: '<p>Página não encontrada</p>' }
const CadConsumidores = { template: '<cad-consumidores></cad-consumidores>' }
const CadPreComunidade = { template: '<cad-pre-comunidade></cad-pre-comunidade>' }
const Produtos = { template: '<lst-produtos></lst-produtos>' }
const Checkout = { template: '<checkout></checkout>' }

const routes = [
    { path: '', component: Home },
    { path: '/', component: Home },
    { path: '/cadconsumidor', component: CadConsumidores },
    { path: '/cadprecomunidade', component: CadPreComunidade },
    { path: '/produtos', component: Produtos },
    { path: '/checkout', component: Checkout }
]

const router = new VueRouter({ routes })

new Vue({ el: '#app', router }).$mount('#app');

new Vue({
    computed: {
        username() {
            return this.$route.params.username
        }
    },
    methods: {
        goBack() {
            window.history.length > 1 ? this.$router.go(-1) : this.$router.push('/')
        }
    }
})


Vue.use(VToaster, { timeout: 5000 })