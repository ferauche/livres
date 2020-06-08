import axios from "axios";

export default {
	buscaPorCep: (cep)=>{
		return axios.get('https://viacep.com.br/ws/'+cep+'/json/');
	}
};