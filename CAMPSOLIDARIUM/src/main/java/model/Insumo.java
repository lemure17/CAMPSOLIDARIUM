package model;
import java.io.Serializable;

public class Insumo implements Serializable{
    private int codigo;
    private String nome;
    private String marca;
    private String categoria;

    public Insumo() {}

    public Insumo(int codigo, String nome, String marca, String categoria) {
        this.codigo = codigo;
        this.nome = nome;
        this.marca = marca;
        this.categoria = categoria;
    }

    public int getCodigo() { return codigo; }
    public void setCodigo(int codigo) { this.codigo = codigo; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getMarca() { return marca; }
    public void setMarca(String marca) { this.marca = marca; }
    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }
}