!function a(b,c,d){function e(g,h){if(!c[g]){if(!b[g]){var i="function"==typeof require&&require;if(!h&&i)return i(g,!0);if(f)return f(g,!0);var j=new Error("Cannot find module '"+g+"'");throw j.code="MODULE_NOT_FOUND",j}var k=c[g]={exports:{}};b[g][0].call(k.exports,function(a){var c=b[g][1][a];return e(c?c:a)},k,k.exports,a,b,c,d)}return c[g].exports}for(var f="function"==typeof require&&require,g=0;g<d.length;g++)e(d[g]);return e}({1:[function(a,b,c){function d(a){this.message=a}function e(a){var b=String(a).replace(/=+$/,"");if(b.length%4==1)throw new d("'atob' failed: The string to be decoded is not correctly encoded.");for(var c,e,g=0,h=0,i="";e=b.charAt(h++);~e&&(c=g%4?64*c+e:e,g++%4)?i+=String.fromCharCode(255&c>>(-2*g&6)):0)e=f.indexOf(e);return i}var f="ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=";d.prototype=new Error,d.prototype.name="InvalidCharacterError",b.exports="undefined"!=typeof window&&window.atob&&window.atob.bind(window)||e},{}],2:[function(a,b,c){function d(a){return decodeURIComponent(e(a).replace(/(.)/g,function(a,b){var c=b.charCodeAt(0).toString(16).toUpperCase();return c.length<2&&(c="0"+c),"%"+c}))}var e=a("./atob");b.exports=function(a){var b=a.replace(/-/g,"+").replace(/_/g,"/");switch(b.length%4){case 0:break;case 2:b+="==";break;case 3:b+="=";break;default:throw"Illegal base64url string!"}try{return d(b)}catch(c){return e(b)}}},{"./atob":1}],3:[function(a,b,c){"use strict";function d(a){this.message=a}var e=a("./base64_url_decode");d.prototype=new Error,d.prototype.name="InvalidTokenError",b.exports=function(a,b){if("string"!=typeof a)throw new d("Invalid token specified");b=b||{};var c=b.header===!0?0:1;try{return JSON.parse(e(a.split(".")[c]))}catch(f){throw new d("Invalid token specified: "+f.message)}},b.exports.InvalidTokenError=d},{"./base64_url_decode":2}],4:[function(a,b,c){(function(b){var c=a("./lib/index");"function"==typeof b.window.define&&b.window.define.amd?b.window.define("jwt_decode",function(){return c}):b.window&&(b.window.jwt_decode=c)}).call(this,"undefined"!=typeof global?global:"undefined"!=typeof self?self:"undefined"!=typeof window?window:{})},{"./lib/index":3}]},{},[4]);

function getCookie(name) {
    function escape(s) {
        return s.replace(/([.*+?\^$(){}|\[\]\/\\])/g, '\\$1');
    }
    var match = document.cookie.match(RegExp('(?:^|;\\s*)' + escape(name) + '=([^;]*)'));
    return match ? match[1] : null;
}

function jsonToUsuario(json) {
    return {
        "nome": json.nome,
        "email": json.email,
        "senha": "",
        "cpf": json.cpf,
        "nascimento": json.nascimento,

        "enderecoRua": json.endereco.rua,
        "enderecoNumero": json.endereco.numero,
        "enderecoBairro": json.endereco.bairro,
        "enderecoCep": json.endereco.cep,
        "enderecoCidade": json.endereco.cidade,
        "enderecoEstado": json.endereco.estado,

        "cartaoCreditoNumero": json.cartaoCredito.numero,
        "cartaoCreditoTitular": json.cartaoCredito.titular,
        "cartaoCreditoExpiracao": json.cartaoCredito.expiracao,
        "cartaoCreditoCVV": json.cartaoCredito.cvv,
        "cartaoCreditoBandeira": json.cartaoCredito.bandeira,

        assinatura: json.assinatura,
    }
}

function handleError(res) {
    if (res.status === 403) {
        return Swal.fire({
            icon: 'error',
            title: 'Acesso negado',
            text: 'Faça login para continuar',
            willClose: function () {
                window.location.href = "/login.html";
            }
        });
    } else if (res.status === 404) {
        return Swal.fire({
            icon: 'error',
            title: 'Conteúdo não encontrado!',
            willClose: function () {
                window.location.href = "/";
            }
        });
    } else {
        console.log(res);
        return Swal.fire({
            icon: 'error',
            title: 'Oops...',
            text: 'Erro: ' + JSON.stringify(res),
        });
    }
}

function getUser() {
    var cookie = getCookie("bearer");
    if (cookie !== null) {
        return jwt_decode(cookie);
    }
    
    return null;
}

function createGenero() {
    app = this;
    Swal.fire({
        title: 'Gênero:',
        input: 'text',
        inputAttributes: {
            autocapitalize: 'off'
        },
        showCancelButton: true,
        confirmButtonText: 'Cadastrar',
        showLoaderOnConfirm: true,
        preConfirm: function (nome) {
            return $.ajax({
                url: "/api/genero/",
                contentType: "application/json",
                method: "POST",
                data: JSON.stringify({nome: nome}),
                headers: {
                    "Authorization": getCookie("bearer"),
                }
            }).done(function (res) {
                app.generos.push(res);
                app.serie.idGenero = res.idGenero;
                return res;
            }).catch(handleError);
        },
        allowOutsideClick: () => !Swal.isLoading()
    }).then((result) => {
    });
}