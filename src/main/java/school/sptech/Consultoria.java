package school.sptech;

import school.sptech.especialistas.DesenvolvedorMobile;
import school.sptech.especialistas.DesenvolvedorWeb;

import java.util.ArrayList;
import java.util.List;

public class Consultoria {
    private String nome;
    private Integer vagas;
    private List<Desenvolvedor> desenvolvedores;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getVagas() {
        return vagas;
    }

    public void setVagas(Integer vagas) {
        this.vagas = vagas;
    }

    public Consultoria() {
        desenvolvedores = new ArrayList<>();
    }

    public Consultoria(String nome, Integer vagas) {
        this.nome = nome;
        this.vagas = vagas;
        desenvolvedores = new ArrayList<>();
    }

    public Boolean existePorNome (String nome) {
        Integer trava = 0;
        for (int i = 0; i < desenvolvedores.size(); i++) {
            if (desenvolvedores.get(i).getNome().equals(nome)){
                trava = 1;
            }
        }
        if (trava == 0){
            return false;
        }
        return true;
    }

    public void contratar (Desenvolvedor desenvolvedor){
        if (desenvolvedores.size() < vagas){
            desenvolvedores.add(desenvolvedor);
        }
    }

    public void contratarFullstack (DesenvolvedorWeb desenvolvedor){
        if (desenvolvedor.isFullstack()){
            desenvolvedores.add(desenvolvedor);
        }
    }

    public Integer qtdDesenvolvedoresMobile(){
        Integer trava = 0;
        for (int i = 0; i < desenvolvedores.size(); i++) {
            if(desenvolvedores.get(i) instanceof DesenvolvedorMobile){
                trava ++;
            }
        }
        return trava;
    }

    public Double getTotalSalarios(){
        Double parametro = 0.0;
        for (int i = 0; i < desenvolvedores.size(); i++) {
            parametro = parametro + desenvolvedores.get(i).calcularSalario();
        }
        return parametro;
    }

    public Desenvolvedor buscarMenorSalario(){
        if(desenvolvedores.isEmpty()){
            return null;
        }
        Double salarioBase = desenvolvedores.get(0).calcularSalario();
        Desenvolvedor dev = desenvolvedores.get(0);
        for (int i = 0; i < desenvolvedores.size(); i++) {
            if (salarioBase > desenvolvedores.get(i).calcularSalario()){
                salarioBase = desenvolvedores.get(i).calcularSalario();
                dev = desenvolvedores.get(i);
            }
        }
        return dev;
    }

    public List<Desenvolvedor> buscarPorSalarioMaiorIgualQue(Double salario){
        List<Desenvolvedor> devCorreto = new ArrayList<>();
        for (int i = 0; i < desenvolvedores.size(); i++) {
            if (desenvolvedores.get(i).calcularSalario()>= salario){
                devCorreto.add(desenvolvedores.get(i));
            }
        }
        return devCorreto;
    }



    public Desenvolvedor getDevPorNome(String nome){
        Desenvolvedor parametro = null;
        for (int i = 0; i < desenvolvedores.size(); i++) {
            if (desenvolvedores.get(i).getNome().equals(nome)){
                parametro = desenvolvedores.get(i);
            }
        }
        return parametro;
    }


    public List<Desenvolvedor> buscarPorTecnologia(String tecnologia) {
        List<Desenvolvedor> desenvolvedoresFiltrados = new ArrayList<>();

        if (desenvolvedores != null && tecnologia != null) {
            for (int i = 0; i < desenvolvedores.size(); i++) {
                Desenvolvedor desenvolvedordaVez = desenvolvedores.get(i);
                if(desenvolvedordaVez instanceof DesenvolvedorWeb){
                    if(((DesenvolvedorWeb) desenvolvedordaVez).getFrontend().contains(tecnologia)){
                        desenvolvedoresFiltrados.add(desenvolvedordaVez);
                    }
                    if(((DesenvolvedorWeb) desenvolvedordaVez).getSgbd().contains(tecnologia)){
                        desenvolvedoresFiltrados.add(desenvolvedordaVez);
                    }
                    if (((DesenvolvedorWeb) desenvolvedordaVez).getBackend().contains(tecnologia)){
                        desenvolvedoresFiltrados.add(desenvolvedordaVez);
                    }
                }
                if(desenvolvedordaVez instanceof DesenvolvedorMobile){
                    if(((DesenvolvedorMobile) desenvolvedordaVez).getPlataforma().contains(tecnologia)){
                        desenvolvedoresFiltrados.add(desenvolvedordaVez);
                    }
                    if(((DesenvolvedorMobile) desenvolvedordaVez).getLinguagem().contains(tecnologia)){
                        desenvolvedoresFiltrados.add(desenvolvedordaVez);
                    }
                }
            }
        }

        return desenvolvedoresFiltrados;
    }

    public Double getTotalSalariosPorTecnologia(String tecnologia){
        Double salario = 0.0;
        List<Desenvolvedor> tecnologias = buscarPorTecnologia(tecnologia);
        for (Desenvolvedor desenvolvedor : tecnologias){
            salario += desenvolvedor.calcularSalario();
        }

        return salario;
    }


}
