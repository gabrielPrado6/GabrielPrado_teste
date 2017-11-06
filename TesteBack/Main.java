package com.Gabriel;
//Read
import java.awt.*;
import java.io.IOException;
//SQL
import java.sql.*;

//Java Utilidades
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

//Formato de Custome
class Custome{

    //Variaveis de Custome
    public String nome;
    public long CPF_CNPJ;
    public boolean ativo;
    public float total;

    //Construtor Custome
    public Custome(String nNome, long nCPF_CNPJ, boolean nAtivo, float nTotal){
        nome = nNome;
        CPF_CNPJ = nCPF_CNPJ;
        ativo = nAtivo;
        total = nTotal;
    }

    public Custome() {
    }
}

public class Main {


    public static void main(String[] args) throws IOException, SQLException {
        //String para navegação entre funções
        char function;

        //Scaner
        Scanner scan = new Scanner(System.in);

        //Loop de funções
        do {
            //Menu de funções
            System.out.println("I_nsere | M_edia | E_xit (Digite a Primeira letra da função)");
            function = (char) scan.next().charAt(0);

            //Insersão de linhas
            if (function == 'I') {

                //Criação da lista de customes
                List<Custome> customes = new ArrayList<Custome>();

                //Boolean de Repetição
                boolean exitInsert = false;
                do {
                    System.out.println("Inserindo novo Cliente");

                    //Chama metodo de criação de um Custome
                    // e adiciona a lista de Customes
                    customes.add(newCustome());

                    //Verifica Nova entrada
                    System.out.println("Desenja inserir um novo Cliente? S_im N_ão ");
                    char answer = (char) scan.next().charAt(0);
                    if(answer == 'N'){
                        exitInsert = true;
                    }
                } while (!exitInsert);

                //Chama metodo de Inserção via SQL
                insertDB(customes);
            }
            //Calculo da média
            else if (function == 'M') {

                //Chama metodo para o calculo da media
                mediaDB();
            }
        }
        while (function != 'E');
    }

    //Metodo de Criação de um Custome
    private static Custome newCustome() {
        //Scaner
        Scanner scan = new Scanner(System.in);

        //Controle de erro de entrada
        try {
            //Entrada de nome
            System.out.println("Digite o nome:");
            String Nome = scan.nextLine();

            //Entrada de CPF
            System.out.println("Digite o CPF ou CNPj:");
            long CPF_CNPJ = scan.nextLong();

            //Loop de entrada True ou False
            boolean error = true;
            boolean Ativo = false;
            do {
                //Entrad por Char
                System.out.println("O usuario esta: A_tivo ou D_esativado:");
                char Bool = (char) scan.next().charAt(0);

                //Verificação de entrada
                if (Bool == 'A') {
                    Ativo = true;
                    error = false;
                } else if (Bool == 'D') {
                    Ativo = false;
                    error = false;
                }
            } while (error);

            //Entrada de Valor
            System.out.println("Digite o saldo do cliente:");
            float Valor = scan.nextFloat();

            //Retorno do novo Custome
            return new Custome(Nome, CPF_CNPJ, Ativo, Valor);
        }
        //retorno de erros
        catch (Exception e){
            System.out.println("Informação inserida não confere");
            return new Custome();
        }

    }

    //Inserção via SQL
    private static void insertDB(List<Custome> customes) throws SQLException{
        //Tratamento de erros
        try {
            //Iniciação de conexão e variavais
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/testeback", "root", "");
            Statement stmt = conn.createStatement();

            //Formatação de numeros
            NumberFormat nf = NumberFormat.getNumberInstance(Locale.US);

            //Loop de inserção de Customes
            for (int i = 0; i < customes.size(); i++){
                //String de Inserção com os valores de Custome formatados
                String sqlInsert = String.format("INSERT INTO tb_customer_account(NM_CUSTOMER, CPF_CNPJ, IS_ACTIVE, VL_TOTAL) VALUES ('%s', %d, %b, %s)", customes.get(i).nome, customes.get(i).CPF_CNPJ, customes.get(i).ativo, Float.toString(customes.get(i).total).replace(',','.') );
                stmt.executeUpdate(sqlInsert);
            }

        }
        //Tratamento de erros
        catch (Exception e) {
            System.out.println("Erro de conexão tente novamente");
        }
    }

    //Media bia SQL
    private static void mediaDB() throws SQLException{
        //Metod de tratamento
        try {
            //Iniciação de conexão e variavais
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/testeback", "root", "");
            Statement stmt = conn.createStatement();
            ResultSet result = null;

            //String da Query de media
            String media = "SELECT AVG(VL_TOTAL) AS media FROM TB_CUSTOMER_ACCOUNT WHERE VL_TOTAL > 560 AND ID_CUSTOMER BETWEEN  1500 AND 2700";
            stmt.executeQuery(media);

            //Resultado da media
            result = stmt.getResultSet();
            if(result != null && result.next()){
                System.out.println(String.format("%.2f", result.getFloat("media")));
            }

            //String da Query Select da media
            String selectMedia = "SELECT NM_CUSTOMER AS NOME, VL_TOTAL AS VALOR, CPF_CNPJ as DOCUMENTO FROM TB_CUSTOMER_ACCOUNT WHERE VL_TOTAL > 560 AND ID_CUSTOMER BETWEEN  1500 AND 2700 ORDER BY VALOR DESC ";
            stmt.executeQuery(selectMedia);

            //Resltado do Select media
            result = stmt.getResultSet();

            //Cabeçario da tabela
            System.out.println("+--------------------+---------------+------------+");
            System.out.println(String.format("|%20s|%15s|%12s|", "NOME","DOCUMENTO","VALOR" ));
            System.out.println("+--------------------+---------------+------------+");

            //Loop de impreção de resultados
            while (result.next()){
                System.out.println(String.format("|%20s|%15s|%12s|", result.getString("NOME"), result.getString("DOCUMENTO"),result.getString("VALOR")));
                System.out.println("+--------------------+---------------+------------+");
            }
        }

        //Tratamento de erros de conexão
        catch (Exception e) {
            System.out.println("Erro de conexão tente novamente");
        }
    }
}
