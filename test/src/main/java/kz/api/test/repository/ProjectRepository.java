package kz.api.test.repository;

import kz.api.test.model.ProjectRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface ProjectRepository extends CrudRepository<ProjectRecord, String>
{
}
