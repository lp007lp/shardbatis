//package com.rookiefly.open.shardbatis.converter;
//
//import net.sf.jsqlparser.expression.AllComparisonExpression;
//import net.sf.jsqlparser.expression.AnyComparisonExpression;
//import net.sf.jsqlparser.expression.BinaryExpression;
//import net.sf.jsqlparser.expression.CaseExpression;
//import net.sf.jsqlparser.expression.DateValue;
//import net.sf.jsqlparser.expression.DoubleValue;
//import net.sf.jsqlparser.expression.Expression;
//import net.sf.jsqlparser.expression.ExpressionVisitor;
//import net.sf.jsqlparser.expression.Function;
//import net.sf.jsqlparser.expression.InverseExpression;
//import net.sf.jsqlparser.expression.JdbcParameter;
//import net.sf.jsqlparser.expression.LongValue;
//import net.sf.jsqlparser.expression.NullValue;
//import net.sf.jsqlparser.expression.Parenthesis;
//import net.sf.jsqlparser.expression.StringValue;
//import net.sf.jsqlparser.expression.TimeValue;
//import net.sf.jsqlparser.expression.TimestampValue;
//import net.sf.jsqlparser.expression.WhenClause;
//import net.sf.jsqlparser.expression.operators.arithmetic.Addition;
//import net.sf.jsqlparser.expression.operators.arithmetic.BitwiseAnd;
//import net.sf.jsqlparser.expression.operators.arithmetic.BitwiseOr;
//import net.sf.jsqlparser.expression.operators.arithmetic.BitwiseXor;
//import net.sf.jsqlparser.expression.operators.arithmetic.Concat;
//import net.sf.jsqlparser.expression.operators.arithmetic.Division;
//import net.sf.jsqlparser.expression.operators.arithmetic.Multiplication;
//import net.sf.jsqlparser.expression.operators.arithmetic.Subtraction;
//import net.sf.jsqlparser.expression.operators.conditional.AndExpression;
//import net.sf.jsqlparser.expression.operators.conditional.OrExpression;
//import net.sf.jsqlparser.expression.operators.relational.Between;
//import net.sf.jsqlparser.expression.operators.relational.EqualsTo;
//import net.sf.jsqlparser.expression.operators.relational.ExistsExpression;
//import net.sf.jsqlparser.expression.operators.relational.ExpressionList;
//import net.sf.jsqlparser.expression.operators.relational.GreaterThan;
//import net.sf.jsqlparser.expression.operators.relational.GreaterThanEquals;
//import net.sf.jsqlparser.expression.operators.relational.InExpression;
//import net.sf.jsqlparser.expression.operators.relational.IsNullExpression;
//import net.sf.jsqlparser.expression.operators.relational.ItemsListVisitor;
//import net.sf.jsqlparser.expression.operators.relational.LikeExpression;
//import net.sf.jsqlparser.expression.operators.relational.Matches;
//import net.sf.jsqlparser.expression.operators.relational.MinorThan;
//import net.sf.jsqlparser.expression.operators.relational.MinorThanEquals;
//import net.sf.jsqlparser.expression.operators.relational.NotEqualsTo;
//import net.sf.jsqlparser.schema.Column;
//import net.sf.jsqlparser.schema.Table;
//import net.sf.jsqlparser.statement.Statement;
//import net.sf.jsqlparser.statement.select.FromItemVisitor;
//import net.sf.jsqlparser.statement.select.Join;
//import net.sf.jsqlparser.statement.select.PlainSelect;
//import net.sf.jsqlparser.statement.select.Select;
//import net.sf.jsqlparser.statement.select.SelectVisitor;
//import net.sf.jsqlparser.statement.select.SubJoin;
//import net.sf.jsqlparser.statement.select.SubSelect;
//import net.sf.jsqlparser.statement.select.Union;
//
//import java.util.Iterator;
//
///**
// * @author sean.he
// */
//public class SelectSqlConverterBak extends AbstractSqlConverter {
//
//    /*
//     * (non-Javadoc)
//     *
//     * @see
//     * com.google.code.shardbatis.converter.AbstractSqlConverter#doConvert(net
//     * .sf.jsqlparser.statement.Statement, java.lang.Object, java.lang.String)
//     */
//    @Override
//    protected Statement doConvert(Statement statement, final Object params,
//                                  final String mapperId) {
//        if (!(statement instanceof Select)) {
//            throw new IllegalArgumentException(
//                    "The argument statement must is instance of Select.");
//        }
//        TableNameModifier modifier = new TableNameModifier(params, mapperId);
//        ((Select) statement).getSelectBody().accept(modifier);
//        return statement;
//    }
//
//    private class TableNameModifier implements SelectVisitor, FromItemVisitor,
//            ExpressionVisitor, ItemsListVisitor {
//        private Object params;
//        private String mapperId;
//
//        TableNameModifier(Object params, String mapperId) {
//            this.params = params;
//            this.mapperId = mapperId;
//        }
//
//        @Override
//        @SuppressWarnings("unchecked")
//        public void visit(PlainSelect plainSelect) {
//            plainSelect.getFromItem().accept(this);
//
//            if (plainSelect.getJoins() != null) {
//                for (Iterator joinsIt = plainSelect.getJoins().iterator(); joinsIt
//                        .hasNext(); ) {
//                    Join join = (Join) joinsIt.next();
//                    join.getRightItem().accept(this);
//                }
//            }
//            if (plainSelect.getWhere() != null) {
//                plainSelect.getWhere().accept(this);
//            }
//
//        }
//
//        @Override
//        @SuppressWarnings("unchecked")
//        public void visit(Union union) {
//            for (Iterator iter = union.getPlainSelects().iterator(); iter
//                    .hasNext(); ) {
//                PlainSelect plainSelect = (PlainSelect) iter.next();
//                visit(plainSelect);
//            }
//        }
//
//        @Override
//        public void visit(Table tableName) {
//            String table = tableName.getName();
//            table = convertTableName(table, params, mapperId);
//            // convert table name
//            tableName.setName(table);
//        }
//
//        @Override
//        public void visit(SubSelect subSelect) {
//            subSelect.getSelectBody().accept(this);
//        }
//
//        @Override
//        public void visit(Addition addition) {
//            visitBinaryExpression(addition);
//        }
//
//        @Override
//        public void visit(AndExpression andExpression) {
//            visitBinaryExpression(andExpression);
//        }
//
//        @Override
//        public void visit(Between between) {
//            between.getLeftExpression().accept(this);
//            between.getBetweenExpressionStart().accept(this);
//            between.getBetweenExpressionEnd().accept(this);
//        }
//
//        @Override
//        public void visit(Column tableColumn) {
//        }
//
//        @Override
//        public void visit(Division division) {
//            visitBinaryExpression(division);
//        }
//
//        @Override
//        public void visit(DoubleValue doubleValue) {
//        }
//
//        @Override
//        public void visit(EqualsTo equalsTo) {
//            visitBinaryExpression(equalsTo);
//        }
//
//        @Override
//        public void visit(Function function) {
//        }
//
//        @Override
//        public void visit(GreaterThan greaterThan) {
//            visitBinaryExpression(greaterThan);
//        }
//
//        @Override
//        public void visit(GreaterThanEquals greaterThanEquals) {
//            visitBinaryExpression(greaterThanEquals);
//        }
//
//        @Override
//        public void visit(InExpression inExpression) {
//            inExpression.getLeftExpression().accept(this);
//            inExpression.getItemsList().accept(this);
//        }
//
//        @Override
//        public void visit(InverseExpression inverseExpression) {
//            inverseExpression.getExpression().accept(this);
//        }
//
//        @Override
//        public void visit(IsNullExpression isNullExpression) {
//        }
//
//        @Override
//        public void visit(JdbcParameter jdbcParameter) {
//        }
//
//        @Override
//        public void visit(LikeExpression likeExpression) {
//            visitBinaryExpression(likeExpression);
//        }
//
//        @Override
//        public void visit(ExistsExpression existsExpression) {
//            existsExpression.getRightExpression().accept(this);
//        }
//
//        @Override
//        public void visit(LongValue longValue) {
//        }
//
//        @Override
//        public void visit(MinorThan minorThan) {
//            visitBinaryExpression(minorThan);
//        }
//
//        @Override
//        public void visit(MinorThanEquals minorThanEquals) {
//            visitBinaryExpression(minorThanEquals);
//        }
//
//        @Override
//        public void visit(Multiplication multiplication) {
//            visitBinaryExpression(multiplication);
//        }
//
//        @Override
//        public void visit(NotEqualsTo notEqualsTo) {
//            visitBinaryExpression(notEqualsTo);
//        }
//
//        @Override
//        public void visit(NullValue nullValue) {
//        }
//
//        @Override
//        public void visit(OrExpression orExpression) {
//            visitBinaryExpression(orExpression);
//        }
//
//        @Override
//        public void visit(Parenthesis parenthesis) {
//            parenthesis.getExpression().accept(this);
//        }
//
//        @Override
//        public void visit(Subtraction subtraction) {
//            visitBinaryExpression(subtraction);
//        }
//
//        public void visitBinaryExpression(BinaryExpression binaryExpression) {
//            binaryExpression.getLeftExpression().accept(this);
//            binaryExpression.getRightExpression().accept(this);
//        }
//
//        @Override
//        @SuppressWarnings("unchecked")
//        public void visit(ExpressionList expressionList) {
//            for (Iterator iter = expressionList.getExpressions().iterator(); iter
//                    .hasNext(); ) {
//                Expression expression = (Expression) iter.next();
//                expression.accept(this);
//            }
//
//        }
//
//        @Override
//        public void visit(DateValue dateValue) {
//        }
//
//        @Override
//        public void visit(TimestampValue timestampValue) {
//        }
//
//        @Override
//        public void visit(TimeValue timeValue) {
//        }
//
//        /*
//         * (non-Javadoc)
//         *
//         * @see
//         * net.sf.jsqlparser.expression.ExpressionVisitor#visit(net.sf.jsqlparser
//         * .expression.CaseExpression)
//         */
//        @Override
//        public void visit(CaseExpression caseExpression) {
//        }
//
//        /*
//         * (non-Javadoc)
//         *
//         * @see
//         * net.sf.jsqlparser.expression.ExpressionVisitor#visit(net.sf.jsqlparser
//         * .expression.WhenClause)
//         */
//        @Override
//        public void visit(WhenClause whenClause) {
//        }
//
//        @Override
//        public void visit(AllComparisonExpression allComparisonExpression) {
//            allComparisonExpression.GetSubSelect().getSelectBody().accept(this);
//        }
//
//        @Override
//        public void visit(AnyComparisonExpression anyComparisonExpression) {
//            anyComparisonExpression.GetSubSelect().getSelectBody().accept(this);
//        }
//
//        @Override
//        public void visit(SubJoin subjoin) {
//            subjoin.getLeft().accept(this);
//            subjoin.getJoin().getRightItem().accept(this);
//        }
//
//        @Override
//        public void visit(Concat concat) {
//            visitBinaryExpression(concat);
//        }
//
//        @Override
//        public void visit(Matches matches) {
//            visitBinaryExpression(matches);
//
//        }
//
//        @Override
//        public void visit(BitwiseAnd bitwiseAnd) {
//            visitBinaryExpression(bitwiseAnd);
//
//        }
//
//        @Override
//        public void visit(BitwiseOr bitwiseOr) {
//            visitBinaryExpression(bitwiseOr);
//
//        }
//
//        @Override
//        public void visit(BitwiseXor bitwiseXor) {
//            visitBinaryExpression(bitwiseXor);
//        }
//
//        @Override
//        public void visit(StringValue stringValue) {
//        }
//    }
//
//}
