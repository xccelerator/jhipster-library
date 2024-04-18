package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.BorrowedBook;
import com.mycompany.myapp.repository.BorrowedBookRepository;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.mycompany.myapp.domain.BorrowedBook}.
 */
@Service
@Transactional
public class BorrowedBookService {

    private final Logger log = LoggerFactory.getLogger(BorrowedBookService.class);

    private final BorrowedBookRepository borrowedBookRepository;

    public BorrowedBookService(BorrowedBookRepository borrowedBookRepository) {
        this.borrowedBookRepository = borrowedBookRepository;
    }

    /**
     * Save a borrowedBook.
     *
     * @param borrowedBook the entity to save.
     * @return the persisted entity.
     */
    public BorrowedBook save(BorrowedBook borrowedBook) {
        log.debug("Request to save BorrowedBook : {}", borrowedBook);
        return borrowedBookRepository.save(borrowedBook);
    }

    /**
     * Update a borrowedBook.
     *
     * @param borrowedBook the entity to save.
     * @return the persisted entity.
     */
    public BorrowedBook update(BorrowedBook borrowedBook) {
        log.debug("Request to update BorrowedBook : {}", borrowedBook);
        return borrowedBookRepository.save(borrowedBook);
    }

    /**
     * Partially update a borrowedBook.
     *
     * @param borrowedBook the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<BorrowedBook> partialUpdate(BorrowedBook borrowedBook) {
        log.debug("Request to partially update BorrowedBook : {}", borrowedBook);

        return borrowedBookRepository
            .findById(borrowedBook.getId())
            .map(existingBorrowedBook -> {
                if (borrowedBook.getBorrowDate() != null) {
                    existingBorrowedBook.setBorrowDate(borrowedBook.getBorrowDate());
                }
                if (borrowedBook.getUpdatedDate() != null) {
                    existingBorrowedBook.setUpdatedDate(borrowedBook.getUpdatedDate());
                }

                return existingBorrowedBook;
            })
            .map(borrowedBookRepository::save);
    }

    /**
     * Get all the borrowedBooks.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<BorrowedBook> findAll() {
        log.debug("Request to get all BorrowedBooks");
        return borrowedBookRepository.findAll();
    }

    /**
     * Get one borrowedBook by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<BorrowedBook> findOne(Integer id) {
        log.debug("Request to get BorrowedBook : {}", id);
        return borrowedBookRepository.findById(id);
    }

    /**
     * Delete the borrowedBook by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Integer id) {
        log.debug("Request to delete BorrowedBook : {}", id);
        borrowedBookRepository.deleteById(id);
    }
}


//@Transactional(readOnly = true)
//public Page<User> findByCriteria(UserCriteria criteria, Pageable page) {
//    log.debug("find by criteria : {}, page: {}", criteria, page);
//    final Specification<User> specification = createSpecification(criteria);
//    return userRepository.findAll(specification, page);
//}
//
//protected Specification<User> createSpecification(UserCriteria criteria) {
//    Specification<User> specification = Specification.where(null);
//    if (criteria != null) {
//        // This has to be called first, because the distinct method returns null
//        if (criteria.getSearch() != null) {
//            specification = specification.or(buildStringSpecification(criteria.getSearch(), User_.login));
//            specification = specification.or(buildStringSpecification(criteria.getSearch(), User_.firstName));
//            specification = specification.or(buildStringSpecification(criteria.getSearch(), User_.lastName));
//            specification = specification.or(buildStringSpecification(criteria.getSearch(), User_.email));
//        }else{
//            if (criteria.getAuthorities() != null) {
//                specification =
//                    specification.and(
//                        buildSpecification(criteria.getAuthorities(), root -> root.join(User_.authorities, JoinType.LEFT).get(Authority_.name))
//                    );
//            }
//            if (criteria.getCreatedDate() != null) {
//                specification = specification.and(buildRangeSpecification(criteria.getCreatedDate(), Organization_.createdDate));
//            }
//            if (criteria.getOrganizationId() != null) {
//                specification =
//                    specification.and(
//                        buildSpecification(criteria.getOrganizationId(), root -> root.join(User_.organizations, JoinType.LEFT).get(Organization_.id))
//                    );
//            }
//        }
//    }
//    return specification;
//}
