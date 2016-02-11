// This is a generated file. Not intended for manual editing.
package com.goide.gotemplate.psi.impl;

import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;
import com.goide.gotemplate.psi.*;

public class GoTemplateWithStatementImpl extends GoTemplateStatementImpl implements GoTemplateWithStatement {

  public GoTemplateWithStatementImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof GoTemplateVisitor) ((GoTemplateVisitor)visitor).visitWithStatement(this);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public GoTemplatePipeline getPipeline() {
    return findChildByClass(GoTemplatePipeline.class);
  }

  @Override
  @Nullable
  public GoTemplateStatement getStatement() {
    return findChildByClass(GoTemplateStatement.class);
  }

  @Override
  @Nullable
  public GoTemplateStatementList getStatementList() {
    return findChildByClass(GoTemplateStatementList.class);
  }

}
