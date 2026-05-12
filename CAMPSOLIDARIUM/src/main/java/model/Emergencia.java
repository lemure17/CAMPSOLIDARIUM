package model;

import java.io.Serializable;

public class Emergencia implements Serializable{
    private int codigo;
    private String local;
    private String tipo;
    private String descricao;

    public Emergencia() {}

    public Emergencia(int codigo, String local, String tipo, String descricao) {
        this.codigo = codigo;
        this.local = local;
        this.tipo = tipo;
        this.descricao = descricao;
    }

    public int getCodigo() { return codigo; }
    public void setCodigo(int codigo) { this.codigo = codigo; }
    public String getLocal() { return local; }
    public void setLocal(String local) { this.local = local; }
    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
}