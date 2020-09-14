package com.daniellorenz.exploration;

import java.util.*;
import java.util.stream.*;
import java.io.IOException;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.daniellorenz.repository.Application;
import com.sap.cds.ql.cqn.*;
import com.sap.cds.reflect.*;
import com.sap.cds.impl.parser.TokenParser;
import com.sap.cds.reflect.impl.CdsElementBuilder;
import com.sap.cds.services.impl.utils.CdsModelUtils;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes=Application.class)
public class CdsModelSpringTest {

    @Autowired
    public CdsModel model;

    @Test
    void modelNotNull() {
        Assertions.assertNotNull(model);
    }

    @Test
    void findViewsAndEntities() {
        Map<String, CdsEntity> views = model.entities().filter(ent -> ent.isView())
                .collect(Collectors.toMap(CdsEntity::getQualifiedName, ent -> ent));
        Map<String, CdsEntity> entities = model.entities().filter(ent -> !ent.isView())
                .collect(Collectors.toMap(CdsEntity::getQualifiedName, ent -> ent));

        Assertions.assertAll(
                () -> Assertions.assertTrue(views.containsKey("CatalogService.Books"), "Bookshops.Books not found."),
                () -> Assertions.assertTrue(entities.containsKey("my.bookshop.Books"), "my.bookshop.Books not found."));
    }

    @Test
    void determineEntitiesInView() {
        CdsEntity view = model.getEntity("my.bookshop.Bus");

        CqnStructuredTypeRef ref = view.query().get().ref();
        List<CqnToken> tokens = view.query().get().tokens().collect(Collectors.toList());
        tokens.iterator().next();
        List<CdsElement> elements = view.elements().collect(Collectors.toList());
        List<CqnSelectListItem> items = view.query().get().items();
        items.iterator().next().token();
        CqnSelect select = view.query().get();
        AnalysisResult result2 = CdsModelUtils.getEntityPath(select);
        CqnSource source = select.from();
        ResolvedSegment segment2 = result2.iterator().next();
        // CqnPredicate where = view.query().get().where().get();
        CqnAnalyzer analyzer = CqnAnalyzer.create(model);
        AnalysisResult result = analyzer.analyze(view.query().get());
        ResolvedSegment segment = result.iterator().next();
        
    }
    
    @Test
        public void testCdsModel() throws IOException {
                var optionalView = cdsModel.findEntity("tm.exec.TestActionsView");
                Assert.assertTrue(optionalView.isPresent());
                var view = optionalView.get();
                CqnSelect select = view.query().get();
                CqnSource from = select.from();
                Assert.assertTrue(from.isJoin());
                CqnJoin join = from.asJoin();
                join.left();
                join.right();
                select.columns();
        }

    private CqnToken parseToken(String cqn) throws IOException {
            JsonNode valObject = mapper.readTree(cqn.replace("'", "\""));
            return TokenParser.parse(valObject);
	}
    
}
