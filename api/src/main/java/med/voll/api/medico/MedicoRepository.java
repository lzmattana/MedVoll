package med.voll.api.medico;

import org.springframework.data.jpa.repository.JpaRepository;

// no repo tipo da entidade e atrib primary key
// essa interface serve para usar metodos jparep
public interface MedicoRepository extends JpaRepository<Medico, Long> {
}
