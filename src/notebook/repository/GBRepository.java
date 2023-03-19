package notebook.repository;

import java.util.List;
import java.util.Optional;

import notebook.model.User;

/**
 * �����������, ��� ���������� CreateReadUpdateDelete (CRUD) ��������
 * @param <E> ��� ������ ������
 * @param <I> ��� ID ������ ������ E
 */
public interface GBRepository<E, I> {
    List<E> findAll();
    E create(User user);
    Optional<E> findById(Long id);
    Optional<E> update(Long userId, User update);
    boolean delete(Long id);
}
