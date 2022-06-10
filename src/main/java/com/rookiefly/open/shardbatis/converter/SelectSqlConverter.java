package com.rookiefly.open.shardbatis.converter;

import java.util.Iterator;

import net.sf.jsqlparser.expression.*;
import net.sf.jsqlparser.expression.operators.arithmetic.*;
import net.sf.jsqlparser.expression.operators.conditional.AndExpression;
import net.sf.jsqlparser.expression.operators.conditional.OrExpression;
import net.sf.jsqlparser.expression.operators.conditional.XorExpression;
import net.sf.jsqlparser.expression.operators.relational.*;
import net.sf.jsqlparser.schema.Column;
import net.sf.jsqlparser.schema.Table;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.select.*;
import net.sf.jsqlparser.statement.values.ValuesStatement;

public class SelectSqlConverter extends AbstractSqlConverter {
    @Override
    protected Statement doConvert(Statement statement, Object params, String mapperId) {
        if (!(statement instanceof Select)) {
            throw new IllegalArgumentException("The argument statement must is instance of Select.");
        }

        TableNameModifier modifier = new TableNameModifier(params, mapperId);
        ((Select) statement).getSelectBody().accept(modifier);
        return statement;
    }

    private class TableNameModifier implements SelectVisitor, FromItemVisitor, ExpressionVisitor, ItemsListVisitor {
        private Object params;
        private String mapperId;

        public TableNameModifier(Object params, String mapperId) {
            this.params = params;
            this.mapperId = mapperId;
        }

        @Override
        public void visit(XorExpression xorExpression) {

        }

        @Override
        public void visit(RowGetExpression rowGetExpression) {

        }

        @Override
        public void visit(ArrayConstructor arrayConstructor) {

        }

        @Override
        public void visit(VariableAssignment variableAssignment) {

        }

        @Override
        public void visit(XMLSerializeExpr xmlSerializeExpr) {

        }

        @Override
        public void visit(TimezoneExpression timezoneExpression) {

        }

        @Override
        public void visit(JsonAggregateFunction jsonAggregateFunction) {

        }

        @Override
        public void visit(JsonFunction jsonFunction) {

        }

        @Override
        public void visit(ConnectByRootOperator connectByRootOperator) {

        }

        @Override
        public void visit(OracleNamedFunctionParameter oracleNamedFunctionParameter) {

        }

        @Override
        public void visit(ExpressionList paramExpressionList) {
            Iterator iter = paramExpressionList.getExpressions().iterator();
            while (iter.hasNext()) {
                Expression expression = (Expression) iter.next();
                expression.accept(this);
            }
        }

        @Override
        public void visit(NamedExpressionList namedExpressionList) {

        }

        @Override
        public void visit(MultiExpressionList paramMultiExpressionList) {
            // TODO Auto-generated method stub

        }

        @Override
        public void visit(BitwiseRightShift bitwiseRightShift) {

        }

        @Override
        public void visit(BitwiseLeftShift bitwiseLeftShift) {

        }

        @Override
        public void visit(NullValue paramNullValue) {
            // TODO Auto-generated method stub

        }

        @Override
        public void visit(Function paramFunction) {
            // TODO Auto-generated method stub

        }

        @Override
        public void visit(SignedExpression paramSignedExpression) {
            // TODO Auto-generated method stub

        }

        @Override
        public void visit(JdbcParameter paramJdbcParameter) {
            // TODO Auto-generated method stub

        }

        @Override
        public void visit(JdbcNamedParameter paramJdbcNamedParameter) {
            // TODO Auto-generated method stub

        }

        @Override
        public void visit(DoubleValue paramDoubleValue) {
            // TODO Auto-generated method stub

        }

        @Override
        public void visit(LongValue paramLongValue) {
            // TODO Auto-generated method stub

        }

        @Override
        public void visit(HexValue paramHexValue) {
            // TODO Auto-generated method stub

        }

        @Override
        public void visit(DateValue paramDateValue) {
            // TODO Auto-generated method stub

        }

        @Override
        public void visit(TimeValue paramTimeValue) {
            // TODO Auto-generated method stub

        }

        @Override
        public void visit(TimestampValue paramTimestampValue) {
            // TODO Auto-generated method stub

        }

        @Override
        public void visit(Parenthesis paramParenthesis) {
            paramParenthesis.getExpression().accept(this);

        }

        @Override
        public void visit(StringValue paramStringValue) {
            // TODO Auto-generated method stub

        }

        @Override
        public void visit(Addition paramAddition) {
            visitBinaryExpression(paramAddition);
        }

        @Override
        public void visit(Division paramDivision) {
            visitBinaryExpression(paramDivision);
        }

        @Override
        public void visit(IntegerDivision division) {

        }

        @Override
        public void visit(Multiplication paramMultiplication) {
            // TODO Auto-generated method stub

        }

        @Override
        public void visit(Subtraction paramSubtraction) {
            visitBinaryExpression(paramSubtraction);
        }

        @Override
        public void visit(AndExpression paramAndExpression) {
            visitBinaryExpression(paramAndExpression);
        }

        @Override
        public void visit(OrExpression paramOrExpression) {
            visitBinaryExpression(paramOrExpression);
        }

        @Override
        public void visit(Between between) {
            between.getLeftExpression().accept(this);
            between.getBetweenExpressionStart().accept(this);
            between.getBetweenExpressionEnd().accept(this);
        }

        @Override
        public void visit(EqualsTo paramEqualsTo) {
            visitBinaryExpression(paramEqualsTo);
        }

        @Override
        public void visit(GreaterThan paramGreaterThan) {
            visitBinaryExpression(paramGreaterThan);

        }

        @Override
        public void visit(GreaterThanEquals paramGreaterThanEquals) {
            visitBinaryExpression(paramGreaterThanEquals);
        }

        @Override
        public void visit(InExpression paramInExpression) {
            paramInExpression.getLeftExpression().accept(this);

            paramInExpression.getRightItemsList().accept(this);
            paramInExpression.getRightItemsList().accept(this);
        }

        @Override
        public void visit(FullTextSearch fullTextSearch) {

        }

        @Override
        public void visit(IsNullExpression paramIsNullExpression) {
            // TODO Auto-generated method stub

        }

        @Override
        public void visit(IsBooleanExpression isBooleanExpression) {

        }

        @Override
        public void visit(LikeExpression paramLikeExpression) {
            visitBinaryExpression(paramLikeExpression);
        }

        @Override
        public void visit(MinorThan paramMinorThan) {
            visitBinaryExpression(paramMinorThan);
        }

        @Override
        public void visit(MinorThanEquals paramMinorThanEquals) {
            visitBinaryExpression(paramMinorThanEquals);
        }

        @Override
        public void visit(NotEqualsTo paramNotEqualsTo) {
            visitBinaryExpression(paramNotEqualsTo);
        }

        @Override
        public void visit(Column paramColumn) {
            // TODO Auto-generated method stub
        }

        @Override
        public void visit(CaseExpression paramCaseExpression) {
            // TODO Auto-generated method stub

        }

        @Override
        public void visit(WhenClause paramWhenClause) {
            // TODO Auto-generated method stub

        }

        @Override
        public void visit(ExistsExpression paramExistsExpression) {
            paramExistsExpression.getRightExpression().accept(this);
        }

//        @Override
//        public void visit(AllComparisonExpression paramAllComparisonExpression) {
//            paramAllComparisonExpression.getSubSelect().getSelectBody().accept(this);
//        }

        @Override
        public void visit(AnyComparisonExpression paramAnyComparisonExpression) {
            paramAnyComparisonExpression.getSubSelect().getSelectBody().accept(this);
        }

        @Override
        public void visit(Concat paramConcat) {
            visitBinaryExpression(paramConcat);
        }

        @Override
        public void visit(Matches paramMatches) {
            visitBinaryExpression(paramMatches);

        }

        @Override
        public void visit(BitwiseAnd paramBitwiseAnd) {
            visitBinaryExpression(paramBitwiseAnd);
        }

        @Override
        public void visit(BitwiseOr paramBitwiseOr) {
            visitBinaryExpression(paramBitwiseOr);
        }

        @Override
        public void visit(BitwiseXor paramBitwiseXor) {
            visitBinaryExpression(paramBitwiseXor);
        }

        public void visitBinaryExpression(BinaryExpression binaryExpression) {
            binaryExpression.getLeftExpression().accept(this);
            binaryExpression.getRightExpression().accept(this);
        }

        @Override
        public void visit(CastExpression paramCastExpression) {
            // TODO Auto-generated method stub

        }

        @Override
        public void visit(Modulo paramModulo) {
            // TODO Auto-generated method stub

        }

        @Override
        public void visit(AnalyticExpression paramAnalyticExpression) {
            // TODO Auto-generated method stub

        }

        @Override
        public void visit(ExtractExpression paramExtractExpression) {
            // TODO Auto-generated method stub

        }

        @Override
        public void visit(IntervalExpression paramIntervalExpression) {
            // paramIntervalExpression.getExpression().accept(this);

        }

        @Override
        public void visit(OracleHierarchicalExpression paramOracleHierarchicalExpression) {
            // TODO Auto-generated method stub

        }

        @Override
        public void visit(RegExpMatchOperator paramRegExpMatchOperator) {
            // TODO Auto-generated method stub

        }

        @Override
        public void visit(JsonExpression paramJsonExpression) {
            // TODO Auto-generated method stub

        }

        @Override
        public void visit(JsonOperator jsonOperator) {

        }

        @Override
        public void visit(RegExpMySQLOperator paramRegExpMySQLOperator) {
            // TODO Auto-generated method stub

        }

        @Override
        public void visit(UserVariable paramUserVariable) {
            // TODO Auto-generated method stub

        }

        @Override
        public void visit(NumericBind paramNumericBind) {
            // TODO Auto-generated method stub

        }

        @Override
        public void visit(KeepExpression paramKeepExpression) {
            // TODO Auto-generated method stub

        }

        @Override
        public void visit(MySQLGroupConcat paramMySQLGroupConcat) {
            // TODO Auto-generated method stub

        }

        @Override
        public void visit(ValueListExpression valueListExpression) {

        }

        @Override
        public void visit(RowConstructor paramRowConstructor) {
            // TODO Auto-generated method stub

        }

        @Override
        public void visit(OracleHint oracleHint) {

        }

        @Override
        public void visit(TimeKeyExpression timeKeyExpression) {

        }

        @Override
        public void visit(DateTimeLiteralExpression dateTimeLiteralExpression) {

        }

        @Override
        public void visit(NotExpression notExpression) {

        }

        @Override
        public void visit(NextValExpression nextValExpression) {

        }

        @Override
        public void visit(CollateExpression collateExpression) {

        }

        @Override
        public void visit(SimilarToExpression aThis) {

        }

        @Override
        public void visit(ArrayExpression aThis) {

        }

        @Override
        public void visit(Table paramTable) {
            String table = paramTable.getName();
            table = SelectSqlConverter.this.convertTableName(table, this.params, this.mapperId);

            paramTable.setName(table);
        }

        @Override
        public void visit(SubSelect paramSubSelect) {
            paramSubSelect.getSelectBody().accept(this);
        }

        @Override
        public void visit(SubJoin subJoin) {

        }


        @Override
        public void visit(LateralSubSelect paramLateralSubSelect) {
            // TODO Auto-generated method stub

        }

        @Override
        public void visit(ValuesList paramValuesList) {
            // TODO Auto-generated method stub

        }

        @Override
        public void visit(TableFunction tableFunction) {

        }

        @Override
        public void visit(ParenthesisFromItem parenthesisFromItem) {

        }

        @Override
        public void visit(PlainSelect plainSelect) {
            plainSelect.getFromItem().accept(this);
            if (plainSelect.getJoins() != null) {
                Iterator joinsIt = plainSelect.getJoins().iterator();
                while (joinsIt.hasNext()) {
                    Join join = (Join) joinsIt.next();
                    join.getRightItem().accept(this);
                }
            }
            if (plainSelect.getWhere() != null) {
                plainSelect.getWhere().accept(this);
            }
        }

        @Override
        public void visit(SetOperationList paramSetOperationList) {
            // TODO Auto-generated method stub

        }

        @Override
        public void visit(WithItem paramWithItem) {
            // TODO Auto-generated method stub

        }

        @Override
        public void visit(ValuesStatement valuesStatement) {

        }

    }
}
