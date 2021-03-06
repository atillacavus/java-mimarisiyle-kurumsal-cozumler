package com.usta.cascade;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 * Enterprise Application Client main class.
 *
 */
public class Test4 {
    
    public static void main( String[] args ) {
        EntityManagerFactory emf=Persistence.createEntityManagerFactory("Cascading_PU");
        EntityManager em=emf.createEntityManager();     
        EntityTransaction trx=em.getTransaction();
        
        Kategori kategori=new Kategori();
        kategori.setKategori("Notebook");
        
        Urun urun=new Urun();
        urun.setUrunAdi("Lenovo abc");
        urun.setKategori(kategori);
        
        Urun urun2=new Urun();
        urun2.setUrunAdi("Toshiba def");
        urun2.setKategori(kategori);
          
        
        Saglayici saglayici=new Saglayici();
        saglayici.setSirketAdi("Marmara Bilişim");
        
        Set<Urun> torba=new HashSet<Urun>();
        torba.add(urun);
        torba.add(urun2);
        saglayici.setUrunler(torba);
        
        
      
            trx.begin();
            em.persist(saglayici); // Kaydetmeden önce
            trx.commit(); // Kaydettikten sonra
            
            
            trx.begin();
            saglayici.setSirketAdi("Ege Bilişim"); // Şu anda managed
            em.refresh(saglayici); // Commit olmadan transaction refresh yapılırsa? Veritabanındaki yüklenir.
            trx.commit();
     
            
            
//            trx.begin();
//           // em.detach(saglayici);
//           // em.refresh(saglayici);
//            
//            /* Exception in thread "main" java.lang.IllegalArgumentException: 
//             * Can not refresh not managed object: 
//             * com.usta.mavenproject1.Saglayici@15422a8. */
//            
//            System.out.println("?"+em.contains(saglayici));
//            em.detach(saglayici);
//            System.out.println("?"+em.contains(saglayici));
//            saglayici.setSirketAdi("Ege Bilişim"); // Güncellemeden önce - sonra
// 
//            trx.commit();
            
      
        
        
        
    }
}
