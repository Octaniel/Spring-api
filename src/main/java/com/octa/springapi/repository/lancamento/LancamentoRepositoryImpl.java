package com.octa.springapi.repository.lancamento;

import com.octa.springapi.model.Categoria_;
import com.octa.springapi.model.Lancamento;
import com.octa.springapi.model.Lancamento_;
import com.octa.springapi.model.Pessoa_;
import com.octa.springapi.repository.filter.LancamentoFilter;
import com.octa.springapi.repository.projection.LancamentoResumo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class LancamentoRepositoryImpl implements LancamentoRepositoryQuery {
    @PersistenceContext
    private EntityManager Manager;

    @Override
    public Page<Lancamento> filtrar(LancamentoFilter lancamentoFilter, Pageable pageable) {

        CriteriaBuilder Builder=Manager.getCriteriaBuilder();
        CriteriaQuery<Lancamento> criteria=Builder.createQuery(Lancamento.class);
        Root<Lancamento> root=criteria.from(Lancamento.class);

        Predicate[] predicates=criarPredicates(lancamentoFilter,Builder,root);
        criteria.where(predicates);

        TypedQuery<Lancamento> query=Manager.createQuery(criteria);
        adicionarRestricoesDePagina(query,pageable);
        return new PageImpl<>(query.getResultList(),pageable,total(lancamentoFilter));
    }

    @Override
    public Page<LancamentoResumo> resumo(LancamentoFilter lancamentoFilter, Pageable pageable) {
        CriteriaBuilder builder=Manager.getCriteriaBuilder();
        CriteriaQuery<LancamentoResumo> query=builder.createQuery(LancamentoResumo.class);
        Root<Lancamento> root=query.from(Lancamento.class);

        query.select(builder.construct(LancamentoResumo.class
                ,root.get(Lancamento_.id),root.get(Lancamento_.descricao),root.get(Lancamento_.dataVencimento)
                ,root.get(Lancamento_.dataPagamento),root.get(Lancamento_.valor),root.get(Lancamento_.tipo)
                ,root.get(Lancamento_.categoria).get(Categoria_.nome),root.get(Lancamento_.pessoa).get(Pessoa_.nome)));

        Predicate[] predicates=criarPredicates(lancamentoFilter,builder,root);
        query.where(predicates);

        TypedQuery<LancamentoResumo> typedQuery=Manager.createQuery(query);
        adicionarRestricoesDePagina(typedQuery,pageable);
        return new PageImpl<>(typedQuery.getResultList(),pageable,total(lancamentoFilter));
    }

    private Long total(LancamentoFilter lancamentoFilter) {
        CriteriaBuilder Builder=Manager.getCriteriaBuilder();
        CriteriaQuery<Long> criteria=Builder.createQuery(Long.class);
        Root<Lancamento> root=criteria.from(Lancamento.class);

        Predicate[] predicates=criarPredicates(lancamentoFilter,Builder,root);
        criteria.where(predicates);
        criteria.select(Builder.count(root));
        return Manager.createQuery(criteria).getSingleResult();
    }

    private void adicionarRestricoesDePagina(TypedQuery<?> query, Pageable pageable) {
        query.setFirstResult(pageable.getPageNumber()*pageable.getPageSize());
        query.setMaxResults(pageable.getPageSize());
    }

    private Predicate[] criarPredicates(LancamentoFilter lancamentoFilter, CriteriaBuilder criteriaBuilder, Root<Lancamento> lancamentoRoot) {
        List<Predicate> predicates=new ArrayList<>();
        if(!StringUtils.isEmpty(lancamentoFilter.getDescricao())){
            predicates.add(criteriaBuilder.like(criteriaBuilder.lower(lancamentoRoot.get(Lancamento_.descricao)),"%"+lancamentoFilter.getDescricao().toLowerCase()+"%"));
        }
        if(lancamentoFilter.getDatavencimentoate()!=null){
            predicates.add(criteriaBuilder.lessThanOrEqualTo(lancamentoRoot.get(Lancamento_.dataVencimento),lancamentoFilter.getDatavencimentoate()));
        }
        if(lancamentoFilter.getDatavencimentode()!=null){
            predicates.add(criteriaBuilder.greaterThanOrEqualTo(lancamentoRoot.get(Lancamento_.dataVencimento),lancamentoFilter.getDatavencimentode()));
        }
        return predicates.toArray(new Predicate[predicates.size()]);
    }
}
