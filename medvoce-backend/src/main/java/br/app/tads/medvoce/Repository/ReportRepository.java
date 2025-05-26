package br.app.tads.medvoce.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

import br.app.tads.medvoce.Model.Report;
import java.util.Date;


@Repository
public interface ReportRepository extends JpaRepository<Report, Long> {

List<Report> findByPatientId(Long id);
List<Report> findByIssueDate(Date issueDate);
List<Report> findAll();
    
}
