<?php

use Twig\Environment;
use Twig\Error\LoaderError;
use Twig\Error\RuntimeError;
use Twig\Extension\SandboxExtension;
use Twig\Markup;
use Twig\Sandbox\SecurityError;
use Twig\Sandbox\SecurityNotAllowedTagError;
use Twig\Sandbox\SecurityNotAllowedFilterError;
use Twig\Sandbox\SecurityNotAllowedFunctionError;
use Twig\Source;
use Twig\Template;

/* server/status/processes/index.twig */
class __TwigTemplate_e3e4e5b53761ef683d80b8edf108d35c92fa6880a2e474ad477912a0b880c241 extends \Twig\Template
{
    private $source;
    private $macros = [];

    public function __construct(Environment $env)
    {
        parent::__construct($env);

        $this->source = $this->getSourceContext();

        $this->blocks = [
            'content' => [$this, 'block_content'],
        ];
    }

    protected function doGetParent(array $context)
    {
        // line 1
        return "server/status/base.twig";
    }

    protected function doDisplay(array $context, array $blocks = [])
    {
        $macros = $this->macros;
        // line 2
        $context["active"] = "processes";
        // line 1
        $this->parent = $this->loadTemplate("server/status/base.twig", "server/status/processes/index.twig", 1);
        $this->parent->display($context, array_merge($this->blocks, $blocks));
    }

    // line 3
    public function block_content($context, array $blocks = [])
    {
        $macros = $this->macros;
        // line 4
        echo "
<fieldset id=\"tableFilter\">
  <legend>";
        // line 6
        echo _gettext("Filters");
        echo "</legend>
  <form action=\"server_status_processes.php\" method=\"post\">
    ";
        // line 8
        echo PhpMyAdmin\Url::getHiddenInputs(($context["url_params"] ?? null));
        echo "
    <input class=\"btn btn-secondary\" type=\"submit\" value=\"";
        // line 9
        echo _gettext("Refresh");
        echo "\">
    <div class=\"formelement\">
      <input type=\"checkbox\" name=\"showExecuting\" id=\"showExecuting\" class=\"autosubmit\"";
        // line 11
        echo ((($context["is_checked"] ?? null)) ? (" checked") : (""));
        echo ">
      <label for=\"showExecuting\">
        ";
        // line 13
        echo _gettext("Show only active");
        // line 14
        echo "      </label>
    </div>
  </form>
</fieldset>

";
        // line 19
        echo ($context["server_process_list"] ?? null);
        echo "

";
        // line 21
        echo call_user_func_array($this->env->getFilter('notice')->getCallable(), [_gettext("Note: Enabling the auto refresh here might cause heavy traffic between the web server and the MySQL server.")]);
        echo "

<div class=\"tabLinks\">
  <label>
    ";
        // line 25
        echo _gettext("Refresh rate");
        echo ":

    <select id=\"id_refreshRate\" class=\"refreshRate\" name=\"refreshRate\">
      ";
        // line 28
        $context['_parent'] = $context;
        $context['_seq'] = twig_ensure_traversable([0 => 2, 1 => 3, 2 => 4, 3 => 5, 4 => 10, 5 => 20, 6 => 40, 7 => 60, 8 => 120, 9 => 300, 10 => 600, 11 => 1200]);
        foreach ($context['_seq'] as $context["_key"] => $context["rate"]) {
            // line 29
            echo "        <option value=\"";
            echo twig_escape_filter($this->env, $context["rate"], "html", null, true);
            echo "\"";
            echo ((($context["rate"] == 5)) ? (" selected") : (""));
            echo ">
          ";
            // line 30
            if (($context["rate"] < 60)) {
                // line 31
                echo "            ";
                if (($context["rate"] == 1)) {
                    // line 32
                    echo "              ";
                    echo twig_escape_filter($this->env, sprintf(_gettext("%d second"), $context["rate"]), "html", null, true);
                    echo "
            ";
                } else {
                    // line 34
                    echo "              ";
                    echo twig_escape_filter($this->env, sprintf(_gettext("%d seconds"), $context["rate"]), "html", null, true);
                    echo "
            ";
                }
                // line 36
                echo "          ";
            } else {
                // line 37
                echo "            ";
                if ((($context["rate"] / 60) == 1)) {
                    // line 38
                    echo "              ";
                    echo twig_escape_filter($this->env, sprintf(_gettext("%d minute"), ($context["rate"] / 60)), "html", null, true);
                    echo "
            ";
                } else {
                    // line 40
                    echo "              ";
                    echo twig_escape_filter($this->env, sprintf(_gettext("%d minutes"), ($context["rate"] / 60)), "html", null, true);
                    echo "
            ";
                }
                // line 42
                echo "          ";
            }
            // line 43
            echo "        </option>
      ";
        }
        $_parent = $context['_parent'];
        unset($context['_seq'], $context['_iterated'], $context['_key'], $context['rate'], $context['_parent'], $context['loop']);
        $context = array_intersect_key($context, $_parent) + $_parent;
        // line 45
        echo "    </select>
  </label>
  <a id=\"toggleRefresh\" href=\"#\">
    ";
        // line 48
        echo PhpMyAdmin\Util::getImage("play");
        echo "
    ";
        // line 49
        echo _gettext("Start auto refresh");
        // line 50
        echo "  </a>
</div>

";
    }

    public function getTemplateName()
    {
        return "server/status/processes/index.twig";
    }

    public function isTraitable()
    {
        return false;
    }

    public function getDebugInfo()
    {
        return array (  170 => 50,  168 => 49,  164 => 48,  159 => 45,  152 => 43,  149 => 42,  143 => 40,  137 => 38,  134 => 37,  131 => 36,  125 => 34,  119 => 32,  116 => 31,  114 => 30,  107 => 29,  103 => 28,  97 => 25,  90 => 21,  85 => 19,  78 => 14,  76 => 13,  71 => 11,  66 => 9,  62 => 8,  57 => 6,  53 => 4,  49 => 3,  44 => 1,  42 => 2,  35 => 1,);
    }

    public function getSourceContext()
    {
        return new Source("", "server/status/processes/index.twig", "C:\\xampp\\phpMyAdmin\\templates\\server\\status\\processes\\index.twig");
    }
}
