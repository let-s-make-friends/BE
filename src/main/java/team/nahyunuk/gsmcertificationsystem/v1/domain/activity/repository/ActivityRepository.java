package team.nahyunuk.gsmcertificationsystem.v1.domain.activity.repository;

import org.springframework.data.repository.CrudRepository;
import team.nahyunuk.gsmcertificationsystem.v1.domain.activity.entity.Activity;
import team.nahyunuk.gsmcertificationsystem.v1.domain.activity.type.ActivityCategory;
import team.nahyunuk.gsmcertificationsystem.v1.domain.student.entity.Student;

import java.util.List;

public interface ActivityRepository extends CrudRepository<Activity, Long> {
    List<Activity> findAllByStudent(Student student);
    int countByActivityCategoryAndStudent(ActivityCategory category, Student student);
}
