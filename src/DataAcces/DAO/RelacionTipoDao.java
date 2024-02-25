package DataAcces.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import DataAcces.IDAO;
import DataAcces.SQLiteDataHelper;
import DataAcces.DTO.RelacionTipoDTO;



public class RelacionTipoDao extends SQLiteDataHelper implements IDAO<RelacionTipoDTO> {

    @Override
    public RelacionTipoDTO readBy(Integer id) throws Exception {
        RelacionTipoDTO oS = new RelacionTipoDTO();
        String query =" SELECT * FROM    RelacionTipo WHERE  IdRelacionTipo =   "+ id.toString() ;
        try {
            Connection conn = openConnection();         // conectar a DB     
            Statement  stmt = conn.createStatement();   // CRUD : select * ...    
            ResultSet rs   = stmt.executeQuery(query);  // ejecutar la
            while (rs.next()) {
            
                oS = new RelacionTipoDTO(rs.getInt(1)
                        ,rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5));
                        
            }
        } 
        catch (SQLException e) {
            throw new Exception(e.getMessage());
        }
        return oS;
    }

    public List<RelacionTipoDTO> readAll() throws Exception {
        List <RelacionTipoDTO> lst = new ArrayList<>();
         String query =" SELECT * FROM   RelacionTipo WHERE   Estado = 'A'  ";
        try {
            Connection conn = openConnection();         // conectar a DB     
            Statement  stmt = conn.createStatement();   // CRUD : select * ...    
            ResultSet rs   = stmt.executeQuery(query);    // ejecutar la
            while (rs.next()) {
                    RelacionTipoDTO s = new RelacionTipoDTO(rs.getInt(1)
                    ,rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getString(5));
                lst.add(s);
            }
        } 
        catch (SQLException e) {
            throw new Exception(e.getMessage());
        }
        return lst; 
    }

    @Override
    public boolean create(RelacionTipoDTO entity) throws Exception {
        String query = " INSERT INTO RelacionTipo (Nombre) VALUES (?)";
        try {
            Connection        conn  = openConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, entity.getNombre());
            pstmt.executeUpdate();
            return true;
        } 
        catch (SQLException e) {
            throw new Exception();
        }
    }

    @Override
    public boolean update(RelacionTipoDTO entity) throws Exception {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");  
        LocalDateTime now = LocalDateTime.now();
        String query = " UPDATE RelacionTipo SET Nombre = ?, FechaModifica = ? WHERE IdRelacionTipo = ?";
        try {
            Connection          conn = openConnection();
            PreparedStatement pstmt  = conn.prepareStatement(query);
            pstmt.setString(1, entity.getNombre());
            pstmt.setString(2, dtf.format(now).toString());
            pstmt.setInt(3, entity.getIdRelacionTipo());
            pstmt.executeUpdate();
            return true;
        } 
        catch (SQLException e) {
            throw new Exception();
        }
    }

    @Override
    public boolean delete(Integer id) throws Exception {
        String query = " UPDATE RelacionTipo SET Estado  = ? WHERE IdRelacionTipo = ?";
        try {
            Connection          conn = openConnection();
            PreparedStatement  pstmt = conn.prepareStatement(query);
            pstmt.setString(1, "X");
            pstmt.setInt(2, id);
            pstmt.executeUpdate();
            return true;
        } 
        catch (SQLException e) {
            throw new Exception();
        }
    }
}

