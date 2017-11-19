
export const APIENDPOINT = "http://localhost:8000/AUJ-api";
//export const APIENDPOINT = "https://lesprojets-entrepotos.fr/AUJ-api";

export const getHeader = function () {
    const data = JSON.parse(window.localStorage.getItem('lbUser'))
    const headers = {
        'Accept':'application/json',
        'Authorization':'Bearer ' + data.token
       }
       return headers
}
