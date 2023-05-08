package med.voll.api.medico;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.nio.channels.FileChannel;

// no repo tipo da entidade e atrib primary key
// essa interface serve para usar metodos jparep
public interface MedicoRepository extends JpaRepository<Medico, Long> {
    Page<Medico> findAllByAtivoTrue(Pageable paginacao);
}
