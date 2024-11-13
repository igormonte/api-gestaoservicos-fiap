package br.com.fiap.postech.gestaoservicos.core.domain.estabelecimento;

import br.com.fiap.postech.gestaoservicos.core.domain.estabelecimento.exception.EnderecoNaoPodeSerNuloException;
import lombok.Data;

@Data
public class Endereco {

    private String rua;
    private Integer numero;
    private String bairro;
    private String cidade;
    private String estado;
    private String cep;

    public Endereco(
            String rua,
            Integer numero,
            String bairro,
            String cidade,
            String estado,
            String cep) {

        if(rua == null || rua.isEmpty()) {
            throw new EnderecoNaoPodeSerNuloException("Rua não pode ser nula ou vazia!");
        }
        if(numero == null) {
            throw new EnderecoNaoPodeSerNuloException("Número não pode ser nulo ou vazio!");
        }
        if(bairro == null || bairro.isEmpty()) {
            throw new EnderecoNaoPodeSerNuloException("Bairro não pode ser nulo ou vazio!");
        }
        if(cidade == null || cidade.isEmpty()) {
            throw new EnderecoNaoPodeSerNuloException("Cidade não pode ser nula ou vazia!");
        }
        if(estado == null || estado.isEmpty()) {
            throw new EnderecoNaoPodeSerNuloException("Estado não pode ser nulo ou vazio!");
        }
        if(cep == null || cep.isEmpty()) {
            throw new EnderecoNaoPodeSerNuloException("CEP não pode ser nulo ou vazio!");
        }
        this.rua = rua;
        this.numero = numero;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
        this.cep = cep;

    }

    public String getEnderecoCompleto() {
        return String.format("%s, %d, %s, %s - %s, %s",
                this.rua,
                this.numero,
                this.bairro,
                this.cidade,
                this.estado,
                this.cep);
    }


}
