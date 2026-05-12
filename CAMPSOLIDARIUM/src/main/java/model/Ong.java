package model;

import java.io.Serializable;

public class Ong implements Serializable {
    private static final long serialVersionUID = 1L;

    private int codigo;
    private String nome;
    private String login;
    private String senha;

   // Getters e Setters
    public int getCodigo() { return codigo; }
    public void setCodigo(int codigo) { this.codigo = codigo; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getLogin() { return login; }
    public void setLogin(String login) { this.login = login; }
    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha; }
}