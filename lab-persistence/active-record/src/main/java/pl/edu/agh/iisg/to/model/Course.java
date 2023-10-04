package pl.edu.agh.iisg.to.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import pl.edu.agh.iisg.to.executor.QueryExecutor;

public class Course {

    public static final String TABLE_NAME = "course";

    private static final Logger logger = Logger.getGlobal();

    private final int id;

    private final String name;

    private List<Student> enrolledStudents;

    private boolean isStudentsListDownloaded = false;

    Course(final int id, final String name) {
        this.id = id;
        this.name = name;
    }

    public static Optional<Course> create(final String name) {
        String insertSql = "INSERT INTO course (name) VALUES (?);";
        Object[] args = {
                name
        };

        try {
            int id = QueryExecutor.createAndObtainId(insertSql, args);
            return Course.findById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public static Optional<Course> findById(final int id) {
        String findByIdSql = "SELECT * FROM course WHERE id = ?";
        Object[] args = {
                id
        };

        try (ResultSet rs = QueryExecutor.read(findByIdSql, args)) {
            if (rs.next()) {
                return Optional.of(new Course(
                        rs.getInt("id"),
                        rs.getString("name")
                ));
            } else {
                return Optional.empty();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public boolean enrollStudent(final Student student) {
        String enrollStudentSql = "insert into student_course (student_id,course_id) values(?,?)";
        Object[] args = {
            student.id(),
                this.id
        };

        //TODO
        try {
            int id = QueryExecutor.createAndObtainId(enrollStudentSql, args);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public List<Student> studentList() {
        String findStudentListSql = "select * from student as s" +
                " join student_course as sc on sc.student_id=s.id"+
                " join course as c on c.id=sc.course_id"+
                " where c.id=(?)";
        Object[] args = {
            this.id
        };

        List<Student> resultList = new LinkedList<>();
        try (ResultSet rs = QueryExecutor.read(findStudentListSql, args)) {
            while(rs.next()){
                resultList.add(new Student(rs.getInt("id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getInt("index_number")));
            }
            System.out.println(resultList);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return resultList;
    }

    public List<Student> cachedStudentsList() {
        //TOTO implement
        if(!isStudentsListDownloaded){
            enrolledStudents=this.studentList();
        }
        return enrolledStudents;
    }
    public float getStudentAverageGrade(int studentId){
        String sql="select * from grade where course_id=? and student_id=?";
        Object[] args = {
                this.id,
                studentId
        };
        float average=0;
        int length=0;
        try (ResultSet rs = QueryExecutor.read(sql, args)) {
            while(rs.next()){
                average+=rs.getFloat("grade");
                length+=1;
            }
            average=average/length;
            return average;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }
    public static List<Integer> getStudentCoursesIds(int id){
        LinkedList<Integer> idsList=new LinkedList<>();
        String sql="select * from student_course where student_id=?";
        Object[] args={
                id
        };
        try (ResultSet rs = QueryExecutor.read(sql, args)) {
            while(rs.next()){
                System.out.println(rs.getInt("course_id"));
                idsList.add(rs.getInt("course_id"));
            }
            return idsList;
        } catch (SQLException e) {
            e.printStackTrace();
            return idsList;
        }
    }

    public int id() {
        return id;
    }

    public String name() {
        return name;
    }

    public static class Columns {

        public static final String ID = "id";

        public static final String NAME = "name";

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Course course = (Course) o;

        if (id != course.id) return false;
        return name.equals(course.name);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + name.hashCode();
        return result;
    }
}
