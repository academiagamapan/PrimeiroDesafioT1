package desafio.Modelos;


public class Cliente {
    private String nome;
    private String cpf;
    private Compra compras = new Compra();

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Compra getCompras() {
        return compras;
    }

    public void setCompras(Compra compras) {
        this.compras = compras;
    }
}