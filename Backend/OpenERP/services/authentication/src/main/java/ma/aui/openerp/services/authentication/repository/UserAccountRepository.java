package ma.aui.openerp.services.authentication.repository;

import ma.aui.openerp.services.authentication.model.UserAccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAccountRepository extends JpaRepository<UserAccountEntity, String> {
}
