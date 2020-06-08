import Home from "./components/Home";
import ListaConsumidores from "./components/ListaConsumidores";

import CadConsumidores from "./components/CadConsumidores";
import CadPreComunidade from "./components/CadPreComunidade";
import Produtos from "./components/LstProdutos";
import Checkout from "./components/Checkout";

export default [
  { path: "/", component: Home },
  { path: "/consumidores", component: ListaConsumidores },

  { path: "/consumidores/cadastro", component: CadConsumidores },
  { path: "/cadprecomunidade", component: CadPreComunidade },
  { path: "/produtos", component: Produtos },
  { path: "/checkout", component: Checkout },
];
