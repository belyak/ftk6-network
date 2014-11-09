<%@ page contentType="text/html;charset=UTF-8" language="java" %>
  <div class="row">

    <div class="row bs-wizard" style="border-bottom:0;">
      <div class="col-xs-4 col-xs-offset-2">
        <h1>E-System project:</h1>
      </div>
      <div class="col-xs-6">
        <p>
          <strong>13.</strong>Электронная платежная система. Операции удаленного объекта: создание счета с начальной суммой,
          пополнение счета, выполнение платежа с указанием получателя и цели, просмотр истории операций.
          Сериализуемый объект: платеж (получатель, цель, сумма). Пополнение счета считать особым видом
          платежа и также отображать в истории операций.
        </p>
      </div>
    </div>
    <div class="row bs-wizard" style="border-bottom:0;">
      <div class="col-xs-3 bs-wizard-step complete">
        <div class="text-center bs-wizard-stepnum">Step 1</div>
        <div class="progress"><div class="progress-bar"></div></div>
        <a href="#" class="bs-wizard-dot"></a>
        <div class="bs-wizard-info text-center">ER decomposition.</div>
      </div>

      <div class="col-xs-3 bs-wizard-step complete"><!-- complete -->
        <div class="text-center bs-wizard-stepnum">Step 2</div>
        <div class="progress"><div class="progress-bar"></div></div>
        <a href="#" class="bs-wizard-dot"></a>
        <div class="bs-wizard-info text-center">Entity classes</div>
      </div>

      <div class="col-xs-3 bs-wizard-step active"><!-- complete -->
        <div class="text-center bs-wizard-stepnum">Step 3</div>
        <div class="progress"><div class="progress-bar"></div></div>
        <a href="#" class="bs-wizard-dot"></a>
        <div class="bs-wizard-info text-center">EJB &amp; business logic</div>
      </div>

      <div class="col-xs-3 bs-wizard-step disabled"><!-- active -->
        <div class="text-center bs-wizard-stepnum">Step 4</div>
        <div class="progress"><div class="progress-bar"></div></div>
        <a href="#" class="bs-wizard-dot"></a>
        <div class="bs-wizard-info text-center">JSP pages and some fun</div>
      </div>
    </div>
    <!-- progress end -->
    <div class="col-xs-4">
      <div class="offer offer-default">
        <div class="shape">
          <div class="shape-text">
            JSP
          </div>
        </div>
        <div class="offer-content">
          <h3 class="lead">
            JavaServer Pages (JSP) is a technology that helps software developers create dynamically generated web pages like this:
          </h3>
          <p>
            <!-- HTML generated using hilite.me --><div style="background: #ffffff; overflow:auto;width:auto;border:solid gray;border-width:.1em .1em .1em .8em;padding:.2em .6em;"><pre style="margin: 0; line-height: 125%"><span style="color: #007700">&lt;p&gt;</span>Counting to three:<span style="color: #007700">&lt;/p&gt;</span>
    <span style="color: #008800; font-weight: bold">&lt;%</span> <span style="color: #008800; font-weight: bold">for</span> <span style="color: #333333">(</span><span style="color: #333399; font-weight: bold">int</span> i<span style="color: #333333">=</span><span style="color: #0000DD; font-weight: bold">1</span><span style="color: #333333">;</span> i<span style="color: #333333">&lt;</span><span style="color: #0000DD; font-weight: bold">4</span><span style="color: #333333">;</span> i<span style="color: #333333">++)</span> <span style="color: #333333">{</span> <span style="color: #008800; font-weight: bold">%&gt;</span>
        <span style="color: #007700">&lt;p&gt;</span>This number is <span style="color: #008800; font-weight: bold">&lt;%=</span> i <span style="color: #008800; font-weight: bold">%&gt;</span>.<span style="color: #007700">&lt;/p&gt;</span>
    <span style="color: #008800; font-weight: bold">&lt;%</span> <span style="color: #333333">}</span> <span style="color: #008800; font-weight: bold">%&gt;</span>
<span style="color: #007700">&lt;p&gt;</span>OK.<span style="color: #007700">&lt;/p&gt;</span>
</pre></div>

          </p>
        </div>
      </div>
    </div>
    <div class="col-xs-4">
      <div class="offer offer-success">
        <div class="shape">
          <div class="shape-text">
            J2EE
          </div>
        </div>
        <div class="offer-content">
          <h3 class="lead">
            Java Platform, Enterprise Edition
          </h3>
          <p>
            Java EE is Oracle's enterprise Java computing platform.
            <br> The platform provides an API and runtime environment
            <br> for developing and running enterprise software,
            <br> including network and web services, and other
            <br> large-scale, multi-tiered, scalable, reliable,
            <br> and secure network applications.
          </p>
        </div>
      </div>
    </div>
    <div class="col-xs-4">
      <div class="offer offer-radius offer-primary">
        <div class="shape">
          <div class="shape-text">
            Java
          </div>
        </div>
        <div class="offer-content">
          <h3 class="lead">
            The language of realization
          </h3>
          <p>
            It's not so nice as python,
            <br> but its ecosystem
            <br> is really huge and full
            <br> of many great ideas!
          </p>
        </div>
      </div>
    </div>
    <div class="col-xs-4">
      <div class="offer offer-info">
        <div class="shape">
          <div class="shape-text">
            EJB
          </div>
        </div>
        <div class="offer-content">
          <h3 class="lead">
            Enterprise JavaBeans aka EJB
          </h3>
          <p>
            is a managed, server-side component
            <br> architecture for modular construction
            <br> of enterprise applications.
            <br>
          </p>
        </div>
      </div>
    </div>
  </div>
  <div class="row">
    <div class="col-xs-4">
      <div class="offer offer-warning">
        <div class="shape">
          <div class="shape-text">
            JPA
          </div>
        </div>
        <div class="offer-content">
          <h3 class="lead">
            The Java Persistence API (JPA) is
          </h3>
          <p>
            a Java programming language application programming
            <br/> interface specification that describes the
            <br/> management of relational data in applications
            <br/> using Java Platform, Standard Edition and
            <br/> Java Platform, Enterprise Edition.

            The Java Persistence API originated as part of the work of the JSR 220 Expert Group of the Java Community Process. JPA 2.0 was the work of the JSR 317 Expert Group.
          </p>
        </div>
      </div>
    </div>

  </div>
