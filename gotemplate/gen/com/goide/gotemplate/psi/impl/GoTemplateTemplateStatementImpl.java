// This is a generated file. Not intended for manual editing.
package com.goide.gotemplate.psi.impl;

import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;
import com.goide.gotemplate.psi.*;

public class GoTemplateTemplateStatementImpl extends GoTemplateStatementImpl implements GoTemplateTemplateStatement {

  public GoTemplateTemplateStatementImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof GoTemplateVisitor) ((GoTemplateVisitor)visitor).visitTemplateStatement(this);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public GoTemplatePipeline getPipeline() {
    return findChildByClass(GoTemplatePipeline.class);
  }

  @Override
  @Nullable
  public GoTemplateStringLiteral getStringLiteral() {
    return findChildByClass(GoTemplateStringLiteral.class);
  }

}
