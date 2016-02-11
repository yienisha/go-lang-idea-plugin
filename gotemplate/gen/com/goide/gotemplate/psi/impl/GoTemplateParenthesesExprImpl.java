// This is a generated file. Not intended for manual editing.
package com.goide.gotemplate.psi.impl;

import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;
import com.goide.gotemplate.psi.*;

public class GoTemplateParenthesesExprImpl extends GoTemplateExpressionImpl implements GoTemplateParenthesesExpr {

  public GoTemplateParenthesesExprImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof GoTemplateVisitor) ((GoTemplateVisitor)visitor).visitParenthesesExpr(this);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public GoTemplatePipeline getPipeline() {
    return findChildByClass(GoTemplatePipeline.class);
  }

}
