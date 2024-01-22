package com.gestion.encuesta.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.gestion.encuesta.model.Propuesta;
import com.gestion.encuesta.model.Usuario;
import com.gestion.encuesta.model.VotosPropuesta;

public interface VPropuestaRepository extends JpaRepository<VotosPropuesta, Long>{

	boolean existsByUsuarioIdAndPropuestaId(Long usuarioId, Long propuestaId);

	VotosPropuesta findByPropuestaId(Long idPropuesta);
	VotosPropuesta findByPropuestaAndUsuario(Propuesta propuesta, Usuario	 usuario);

	@Query("SELECT SUM(CASE WHEN vp.votoPositivo = true THEN 1 ELSE 0 END) FROM VotosPropuesta vp WHERE vp.propuesta.id = :idPropuesta")
	int sumarVotosPositivosPorPropuesta(Long idPropuesta);

	boolean existsByUsuarioIdAndPropuestaIdAndVotoPositivo(Long idUsuario, Long idPropuesta, boolean b);
}
