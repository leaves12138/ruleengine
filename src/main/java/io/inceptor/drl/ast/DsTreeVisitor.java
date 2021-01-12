package io.inceptor.drl.ast;

import io.inceptor.drl.drl.datasource.Datasource;
import io.inceptor.drl.drl.datasource.impl.JDBCDatasource;
import io.inceptor.drl.drl.datasource.impl.KafkaDatasource;
import io.inceptor.drl.drl.datasource.impl.RedisDatasource;
import io.inceptor.drl.exceptions.ParseDrlRuntimeException;
import io.inceptor.drl.parser.DrlParser;
import io.inceptor.drl.parser.DsParser;
import io.inceptor.drl.parser.DsParserBaseVisitor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

//thread not safe
public class DsTreeVisitor extends DsParserBaseVisitor<List<Datasource>> {
    private List<Datasource> datasourceList;


    @Override
    public List<Datasource> visitDeclares(DsParser.DeclaresContext ctx) {
        datasourceList = new ArrayList<>();

        return visitChildren(ctx);
    }


    @Override
    public List<Datasource> visitDeclare(DsParser.DeclareContext ctx) {
        DsParser.TypeBranchContext typeBranch = Optional.of(ctx.getRuleContext(DsParser.TypeBranchContext.class, 0))
                .orElseThrow(() -> new ParseDrlRuntimeException(""));
        String name = ctx.Identifier().getText();
        String type = typeBranch.STRING().getText();

        switch (type) {
            case "jdbc": {
                datasourceList.add(getJdbcDatasource(name, ctx));
                return datasourceList;
            }
            case "redis": {
                datasourceList.add(getRedisDatasource(name, ctx));
                return datasourceList;
            }
            case "kafka": {
                datasourceList.add(getKafkaDatasource(name, ctx));
                return datasourceList;
            }
            default: {
                throw new ParseDrlRuntimeException("can't resolve datasource type: " + type);
            }
        }

    }

    private Datasource getJdbcDatasource(String name, DsParser.DeclareContext ctx) {

        DsParser.URLBranchContext urlBranchContext = Optional.of(ctx.getRuleContext(DsParser.URLBranchContext.class, 0))
                .orElseThrow(() -> new ParseDrlRuntimeException("url must exist in jdbc type datasource"));
        DsParser.UserNameBranchContext userNameBranchContext = Optional.of(ctx.getRuleContext(DsParser.UserNameBranchContext.class, 0))
                .orElseThrow(() -> new ParseDrlRuntimeException("username must exist in jdbc type datasource"));
        DsParser.PasswordBranchContext passwordBranchContext = Optional.of(ctx.getRuleContext(DsParser.PasswordBranchContext.class, 0))
                .orElseThrow(() -> new ParseDrlRuntimeException("password must exist in jdbc type datasource"));
        return new JDBCDatasource(name, urlBranchContext.STRING().getText(), userNameBranchContext.STRING().getText(), passwordBranchContext.STRING().getText());
    }

    private Datasource getRedisDatasource(String name, DsParser.DeclareContext ctx) {
        DsParser.HostBranchContext hostBranchContext = ctx.getRuleContext(DsParser.HostBranchContext.class, 0);
        DsParser.PortBranchContext portBranchContext = ctx.getRuleContext(DsParser.PortBranchContext.class, 0);
        DsParser.PasswordBranchContext passwordBranchContext = ctx.getRuleContext(DsParser.PasswordBranchContext.class, 0);
        String host = RedisDatasource.Default_host;
        if (hostBranchContext != null)
            host = hostBranchContext.STRING().getText();
        Integer port = RedisDatasource.Default_port;
        if (portBranchContext != null)
            port = Integer.valueOf(portBranchContext.NUMBER().getText());
        String password = null;
        if (passwordBranchContext != null)
            password = passwordBranchContext.STRING().getText();
        return new RedisDatasource(name, host, port, password);

    }

    private Datasource getKafkaDatasource(String name, DsParser.DeclareContext ctx) {
        Properties properties = new Properties();
        List<DsParser.UserDefineBranchContext> userDefineBranchContexts = ctx.getRuleContexts(DsParser.UserDefineBranchContext.class);
        if (userDefineBranchContexts == null)
            throw new ParseDrlRuntimeException("kafka datasource must have user defined properties");
        for (DsParser.UserDefineBranchContext context : userDefineBranchContexts) {
            String key = context.name.getText();
            Object value = context.value instanceof DrlParser.NumberBranchContext ? Integer.valueOf(context.value.getText()) : context.value.getText();
            properties.put(key, value);
        }
        return new KafkaDatasource(name, properties);
    }

}
