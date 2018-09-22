package main;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.FlushMode;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class FachadaInterna {

    private static FachadaInterna fachadaInterna;
    private final String paqueteEntidades = "entidades";

    public FachadaInterna() {
    }

    public static FachadaInterna getInstance() {
        if (fachadaInterna == null) {
            fachadaInterna = new FachadaInterna();
        }
        return fachadaInterna;
    }

     List<Object> buscar(String claseABuscar, List<DTOCriterio> criterioList) {
        Criteria cr = null;
        try {
            cr = HibernateUtil.getSession().createCriteria(Class.forName(paqueteEntidades + "." + claseABuscar), claseABuscar.toLowerCase());
        } catch (ClassNotFoundException e) {
            System.out.println("Error creating criteria. " + e);
        }
        if (criterioList != null) {
            for (DTOCriterio criterio : criterioList) {
                String atributo = criterio.getAtributo();
                String operacion = criterio.getOperacion();
                Object valor = criterio.getValor();

                switch (operacion) {
                    case "=":
                        cr.add(Restrictions.conjunction(Restrictions.eqOrIsNull(atributo, valor)));
                        break;
                    case "<":
                        cr.add(Restrictions.conjunction(Restrictions.lt(atributo, valor)));
                        break;
                    case ">":
                        cr.add(Restrictions.conjunction(Restrictions.gt(atributo, valor)));
                        break;
                    case "<=":
                        cr.add(Restrictions.conjunction(Restrictions.le(atributo, valor)));
                        break;
                    case ">=":
                        cr.add(Restrictions.conjunction(Restrictions.ge(atributo, valor)));
                        break;
                    case "<>":
                        cr.add(Restrictions.conjunction(Restrictions.ne(atributo, valor)));
                        break;
                    case "contains":
                        //Todos las colecciones deben empezar de la siguiente manera: claseColeccionList
                        //Generamos el nombre de esa colección a partir del atributo
                        String list = String.valueOf(atributo.charAt(0))
                                .toLowerCase() + atributo.substring(1); //materiaList, detalleReposicionList
                        String property = String.format("%s.%s", claseABuscar.toLowerCase(), list);
                        cr.setFetchMode(property, FetchMode.JOIN);
                        cr.createAlias(property, "lista");
                        cr.setFetchMode("lista.OID" + claseABuscar, FetchMode.JOIN);
                        cr.add(Restrictions.eq("lista.OID", ((entidades.Entidad) valor).getOID()));
                        break;
                }

            }
        }

        return cr.list();
    }

    void guardar(Object objeto) {
        HibernateUtil.getSession().save(objeto);
        HibernateUtil.getSession().flush();
    }

    void iniciarTransaccion() {
        HibernateUtil.getSession().beginTransaction();
    }

    void finalizarTransaccion() {
        HibernateUtil.getSession().setFlushMode(FlushMode.NEVER);
        HibernateUtil.getSession().getTransaction().commit();
        HibernateUtil.getSession().close();
        HibernateUtil.getSessionFactory().close();
    }

}
