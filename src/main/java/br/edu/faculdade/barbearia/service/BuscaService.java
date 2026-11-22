package br.edu.faculdade.barbearia.service;

import br.edu.faculdade.barbearia.servico.ServicoResposta;
import br.edu.faculdade.barbearia.repository.ServicoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BuscaService {

    private final ServicoRepository repositorio;

    public BuscaService(ServicoRepository repositorio) {
        this.repositorio = repositorio;
    }

    public List<ServicoResposta> buscar(String categoria, String barbeiro,
                                        Double precoMax, Boolean promocao) {
        return repositorio.todos().stream()
                .filter(s -> categoria == null || s.categoria().equalsIgnoreCase(categoria))
                .filter(s -> barbeiro == null || s.barbeiroSlug().equalsIgnoreCase(barbeiro))
                .filter(s -> precoMax == null || s.preco() <= precoMax)
                .filter(s -> promocao == null || s.promocao() == promocao)
                .map(ServicoResposta::de)
                .toList();
    }
}