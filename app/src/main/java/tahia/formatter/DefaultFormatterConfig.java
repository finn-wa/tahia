package tahia.formatter;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class DefaultFormatterConfig {
    public static final Map<Object, Object> CONFIG;
    static {
        final Map<Object, Object> config = new HashMap<>();
        config.put("org.eclipse.jdt.core.formatter.align_assignment_statements_on_columns", "false");
        config.put("org.eclipse.jdt.core.formatter.align_fields_grouping_blank_lines", "2147483647");
        config.put(
            "org.eclipse.jdt.core.formatter.align_selector_in_method_invocation_on_expression_first_line",
            "false"
        );
        config.put("org.eclipse.jdt.core.formatter.align_type_members_on_columns", "false");
        config.put("org.eclipse.jdt.core.formatter.align_variable_declarations_on_columns", "false");
        config.put("org.eclipse.jdt.core.formatter.align_with_spaces", "false");
        config.put("org.eclipse.jdt.core.formatter.alignment_for_additive_operator", "48");
        config.put("org.eclipse.jdt.core.formatter.alignment_for_annotations_on_enum_constant", "48");
        config.put("org.eclipse.jdt.core.formatter.alignment_for_annotations_on_field", "49");
        config.put("org.eclipse.jdt.core.formatter.alignment_for_annotations_on_local_variable", "49");
        config.put("org.eclipse.jdt.core.formatter.alignment_for_annotations_on_method", "49");
        config.put("org.eclipse.jdt.core.formatter.alignment_for_annotations_on_package", "49");
        config.put("org.eclipse.jdt.core.formatter.alignment_for_annotations_on_parameter", "48");
        config.put("org.eclipse.jdt.core.formatter.alignment_for_annotations_on_type", "48");
        config.put("org.eclipse.jdt.core.formatter.alignment_for_arguments_in_allocation_expression", "48");
        config.put("org.eclipse.jdt.core.formatter.alignment_for_arguments_in_annotation", "48");
        config.put("org.eclipse.jdt.core.formatter.alignment_for_arguments_in_enum_constant", "48");
        config.put("org.eclipse.jdt.core.formatter.alignment_for_arguments_in_explicit_constructor_call", "48");
        config.put("org.eclipse.jdt.core.formatter.alignment_for_arguments_in_method_invocation", "48");
        config.put("org.eclipse.jdt.core.formatter.alignment_for_arguments_in_qualified_allocation_expression", "48");
        config.put("org.eclipse.jdt.core.formatter.alignment_for_assertion_message", "16");
        config.put("org.eclipse.jdt.core.formatter.alignment_for_assignment", "0");
        config.put("org.eclipse.jdt.core.formatter.alignment_for_bitwise_operator", "48");
        config.put("org.eclipse.jdt.core.formatter.alignment_for_compact_if", "48");
        config.put("org.eclipse.jdt.core.formatter.alignment_for_compact_loops", "48");
        config.put("org.eclipse.jdt.core.formatter.alignment_for_conditional_expression", "48");
        config.put("org.eclipse.jdt.core.formatter.alignment_for_conditional_expression_chain", "48");
        config.put("org.eclipse.jdt.core.formatter.alignment_for_enum_constants", "49");
        config.put("org.eclipse.jdt.core.formatter.alignment_for_expressions_in_array_initializer", "48");
        config.put("org.eclipse.jdt.core.formatter.alignment_for_expressions_in_for_loop_header", "48");
        config.put("org.eclipse.jdt.core.formatter.alignment_for_expressions_in_switch_case_with_arrow", "48");
        config.put("org.eclipse.jdt.core.formatter.alignment_for_expressions_in_switch_case_with_colon", "48");
        config.put("org.eclipse.jdt.core.formatter.alignment_for_logical_operator", "50");
        config.put("org.eclipse.jdt.core.formatter.alignment_for_method_declaration", "0");
        config.put("org.eclipse.jdt.core.formatter.alignment_for_module_statements", "48");
        config.put("org.eclipse.jdt.core.formatter.alignment_for_multiple_fields", "16");
        config.put("org.eclipse.jdt.core.formatter.alignment_for_multiplicative_operator", "48");
        config.put("org.eclipse.jdt.core.formatter.alignment_for_parameterized_type_references", "0");
        config.put("org.eclipse.jdt.core.formatter.alignment_for_parameters_in_constructor_declaration", "48");
        config.put("org.eclipse.jdt.core.formatter.alignment_for_parameters_in_method_declaration", "48");
        config.put("org.eclipse.jdt.core.formatter.alignment_for_record_components", "49");
        config.put("org.eclipse.jdt.core.formatter.alignment_for_relational_operator", "0");
        config.put("org.eclipse.jdt.core.formatter.alignment_for_resources_in_try", "49");
        config.put("org.eclipse.jdt.core.formatter.alignment_for_selector_in_method_invocation", "80");
        config.put("org.eclipse.jdt.core.formatter.alignment_for_shift_operator", "0");
        config.put("org.eclipse.jdt.core.formatter.alignment_for_string_concatenation", "16");
        config.put("org.eclipse.jdt.core.formatter.alignment_for_superclass_in_type_declaration", "16");
        config.put("org.eclipse.jdt.core.formatter.alignment_for_superinterfaces_in_enum_declaration", "16");
        config.put("org.eclipse.jdt.core.formatter.alignment_for_superinterfaces_in_record_declaration", "16");
        config.put("org.eclipse.jdt.core.formatter.alignment_for_superinterfaces_in_type_declaration", "16");
        config.put("org.eclipse.jdt.core.formatter.alignment_for_switch_case_with_arrow", "48");
        config.put("org.eclipse.jdt.core.formatter.alignment_for_throws_clause_in_constructor_declaration", "16");
        config.put("org.eclipse.jdt.core.formatter.alignment_for_throws_clause_in_method_declaration", "16");
        config.put("org.eclipse.jdt.core.formatter.alignment_for_type_annotations", "48");
        config.put("org.eclipse.jdt.core.formatter.alignment_for_type_arguments", "48");
        config.put("org.eclipse.jdt.core.formatter.alignment_for_type_parameters", "48");
        config.put("org.eclipse.jdt.core.formatter.alignment_for_union_type_in_multicatch", "48");
        config.put("org.eclipse.jdt.core.formatter.blank_lines_after_imports", "1");
        config.put("org.eclipse.jdt.core.formatter.blank_lines_after_last_class_body_declaration", "0");
        config.put("org.eclipse.jdt.core.formatter.blank_lines_after_package", "1");
        config.put("org.eclipse.jdt.core.formatter.blank_lines_before_abstract_method", "1");
        config.put("org.eclipse.jdt.core.formatter.blank_lines_before_field", "0");
        config.put("org.eclipse.jdt.core.formatter.blank_lines_before_first_class_body_declaration", "0");
        config.put("org.eclipse.jdt.core.formatter.blank_lines_before_imports", "1");
        config.put("org.eclipse.jdt.core.formatter.blank_lines_before_member_type", "1");
        config.put("org.eclipse.jdt.core.formatter.blank_lines_before_method", "1");
        config.put("org.eclipse.jdt.core.formatter.blank_lines_before_new_chunk", "1");
        config.put("org.eclipse.jdt.core.formatter.blank_lines_before_package", "0");
        config.put("org.eclipse.jdt.core.formatter.blank_lines_between_import_groups", "1");
        config.put("org.eclipse.jdt.core.formatter.blank_lines_between_statement_group_in_switch", "0");
        config.put("org.eclipse.jdt.core.formatter.blank_lines_between_type_declarations", "1");
        config.put("org.eclipse.jdt.core.formatter.brace_position_for_annotation_type_declaration", "end_of_line");
        config.put("org.eclipse.jdt.core.formatter.brace_position_for_anonymous_type_declaration", "end_of_line");
        config.put("org.eclipse.jdt.core.formatter.brace_position_for_array_initializer", "end_of_line");
        config.put("org.eclipse.jdt.core.formatter.brace_position_for_block", "end_of_line");
        config.put("org.eclipse.jdt.core.formatter.brace_position_for_block_in_case", "end_of_line");
        config.put("org.eclipse.jdt.core.formatter.brace_position_for_constructor_declaration", "end_of_line");
        config.put("org.eclipse.jdt.core.formatter.brace_position_for_enum_constant", "end_of_line");
        config.put("org.eclipse.jdt.core.formatter.brace_position_for_enum_declaration", "end_of_line");
        config.put("org.eclipse.jdt.core.formatter.brace_position_for_lambda_body", "end_of_line");
        config.put("org.eclipse.jdt.core.formatter.brace_position_for_method_declaration", "end_of_line");
        config.put("org.eclipse.jdt.core.formatter.brace_position_for_record_constructor", "end_of_line");
        config.put("org.eclipse.jdt.core.formatter.brace_position_for_record_declaration", "end_of_line");
        config.put("org.eclipse.jdt.core.formatter.brace_position_for_switch", "end_of_line");
        config.put("org.eclipse.jdt.core.formatter.brace_position_for_type_declaration", "end_of_line");
        config.put("org.eclipse.jdt.core.formatter.comment.align_tags_descriptions_grouped", "false");
        config.put("org.eclipse.jdt.core.formatter.comment.align_tags_names_descriptions", "false");
        config.put("org.eclipse.jdt.core.formatter.comment.clear_blank_lines_in_block_comment", "false");
        config.put("org.eclipse.jdt.core.formatter.comment.clear_blank_lines_in_javadoc_comment", "false");
        config.put("org.eclipse.jdt.core.formatter.comment.count_line_length_from_starting_position", "false");
        config.put("org.eclipse.jdt.core.formatter.comment.format_block_comments", "true");
        config.put("org.eclipse.jdt.core.formatter.comment.format_header", "true");
        config.put("org.eclipse.jdt.core.formatter.comment.format_html", "true");
        config.put("org.eclipse.jdt.core.formatter.comment.format_javadoc_comments", "true");
        config.put("org.eclipse.jdt.core.formatter.comment.format_line_comments", "false");
        config.put("org.eclipse.jdt.core.formatter.comment.format_source_code", "true");
        config.put("org.eclipse.jdt.core.formatter.comment.indent_parameter_description", "true");
        config.put("org.eclipse.jdt.core.formatter.comment.indent_root_tags", "false");
        config.put("org.eclipse.jdt.core.formatter.comment.indent_tag_description", "true");
        config.put("org.eclipse.jdt.core.formatter.comment.insert_new_line_before_root_tags", "insert");
        config.put("org.eclipse.jdt.core.formatter.comment.insert_new_line_between_different_tags", "do not insert");
        config.put("org.eclipse.jdt.core.formatter.comment.insert_new_line_for_parameter", "do not insert");
        config.put("org.eclipse.jdt.core.formatter.comment.line_length", "100");
        config.put("org.eclipse.jdt.core.formatter.comment.new_lines_at_block_boundaries", "true");
        config.put("org.eclipse.jdt.core.formatter.comment.new_lines_at_javadoc_boundaries", "true");
        config.put(
            "org.eclipse.jdt.core.formatter.comment.preserve_white_space_between_code_and_line_comments",
            "true"
        );
        config.put("org.eclipse.jdt.core.formatter.compact_else_if", "true");
        config.put("org.eclipse.jdt.core.formatter.continuation_indentation", "1");
        config.put("org.eclipse.jdt.core.formatter.continuation_indentation_for_array_initializer", "1");
        config.put("org.eclipse.jdt.core.formatter.disabling_tag", "@formatter:off");
        config.put("org.eclipse.jdt.core.formatter.enabling_tag", "@formatter:on");
        config.put("org.eclipse.jdt.core.formatter.format_guardian_clause_on_one_line", "false");
        config.put("org.eclipse.jdt.core.formatter.format_line_comment_starting_on_first_column", "false");
        config.put(
            "org.eclipse.jdt.core.formatter.indent_body_declarations_compare_to_annotation_declaration_header",
            "true"
        );
        config.put("org.eclipse.jdt.core.formatter.indent_body_declarations_compare_to_enum_constant_header", "true");
        config.put(
            "org.eclipse.jdt.core.formatter.indent_body_declarations_compare_to_enum_declaration_header",
            "true"
        );
        config.put("org.eclipse.jdt.core.formatter.indent_body_declarations_compare_to_record_header", "true");
        config.put("org.eclipse.jdt.core.formatter.indent_body_declarations_compare_to_type_header", "true");
        config.put("org.eclipse.jdt.core.formatter.indent_breaks_compare_to_cases", "true");
        config.put("org.eclipse.jdt.core.formatter.indent_empty_lines", "false");
        config.put("org.eclipse.jdt.core.formatter.indent_statements_compare_to_block", "true");
        config.put("org.eclipse.jdt.core.formatter.indent_statements_compare_to_body", "true");
        config.put("org.eclipse.jdt.core.formatter.indent_switchstatements_compare_to_cases", "true");
        config.put("org.eclipse.jdt.core.formatter.indent_switchstatements_compare_to_switch", "true");
        config.put("org.eclipse.jdt.core.formatter.indentation.size", "4");
        config.put("org.eclipse.jdt.core.formatter.insert_new_line_after_annotation_on_enum_constant", "do not insert");
        config.put("org.eclipse.jdt.core.formatter.insert_new_line_after_annotation_on_field", "insert");
        config.put("org.eclipse.jdt.core.formatter.insert_new_line_after_annotation_on_local_variable", "insert");
        config.put("org.eclipse.jdt.core.formatter.insert_new_line_after_annotation_on_method", "insert");
        config.put("org.eclipse.jdt.core.formatter.insert_new_line_after_annotation_on_package", "insert");
        config.put("org.eclipse.jdt.core.formatter.insert_new_line_after_annotation_on_parameter", "do not insert");
        config.put("org.eclipse.jdt.core.formatter.insert_new_line_after_annotation_on_type", "insert");
        config.put("org.eclipse.jdt.core.formatter.insert_new_line_after_label", "do not insert");
        config.put(
            "org.eclipse.jdt.core.formatter.insert_new_line_after_opening_brace_in_array_initializer",
            "do not insert"
        );
        config.put("org.eclipse.jdt.core.formatter.insert_new_line_after_type_annotation", "do not insert");
        config.put("org.eclipse.jdt.core.formatter.insert_new_line_at_end_of_file_if_missing", "insert");
        config.put("org.eclipse.jdt.core.formatter.insert_new_line_before_catch_in_try_statement", "do not insert");
        config.put(
            "org.eclipse.jdt.core.formatter.insert_new_line_before_closing_brace_in_array_initializer",
            "do not insert"
        );
        config.put("org.eclipse.jdt.core.formatter.insert_new_line_before_else_in_if_statement", "do not insert");
        config.put("org.eclipse.jdt.core.formatter.insert_new_line_before_finally_in_try_statement", "do not insert");
        config.put("org.eclipse.jdt.core.formatter.insert_new_line_before_while_in_do_statement", "do not insert");
        config.put("org.eclipse.jdt.core.formatter.insert_space_after_additive_operator", "insert");
        config.put("org.eclipse.jdt.core.formatter.insert_space_after_and_in_type_parameter", "insert");
        config.put("org.eclipse.jdt.core.formatter.insert_space_after_arrow_in_switch_case", "insert");
        config.put("org.eclipse.jdt.core.formatter.insert_space_after_arrow_in_switch_default", "insert");
        config.put("org.eclipse.jdt.core.formatter.insert_space_after_assignment_operator", "insert");
        config.put("org.eclipse.jdt.core.formatter.insert_space_after_at_in_annotation", "do not insert");
        config.put(
            "org.eclipse.jdt.core.formatter.insert_space_after_at_in_annotation_type_declaration",
            "do not insert"
        );
        config.put("org.eclipse.jdt.core.formatter.insert_space_after_bitwise_operator", "insert");
        config.put(
            "org.eclipse.jdt.core.formatter.insert_space_after_closing_angle_bracket_in_type_arguments",
            "do not insert"
        );
        config.put(
            "org.eclipse.jdt.core.formatter.insert_space_after_closing_angle_bracket_in_type_parameters",
            "insert"
        );
        config.put("org.eclipse.jdt.core.formatter.insert_space_after_closing_brace_in_block", "insert");
        config.put("org.eclipse.jdt.core.formatter.insert_space_after_closing_paren_in_cast", "insert");
        config.put("org.eclipse.jdt.core.formatter.insert_space_after_colon_in_assert", "insert");
        config.put("org.eclipse.jdt.core.formatter.insert_space_after_colon_in_case", "insert");
        config.put("org.eclipse.jdt.core.formatter.insert_space_after_colon_in_conditional", "insert");
        config.put("org.eclipse.jdt.core.formatter.insert_space_after_colon_in_for", "insert");
        config.put("org.eclipse.jdt.core.formatter.insert_space_after_colon_in_labeled_statement", "insert");
        config.put("org.eclipse.jdt.core.formatter.insert_space_after_comma_in_allocation_expression", "insert");
        config.put("org.eclipse.jdt.core.formatter.insert_space_after_comma_in_annotation", "insert");
        config.put("org.eclipse.jdt.core.formatter.insert_space_after_comma_in_array_initializer", "insert");
        config.put(
            "org.eclipse.jdt.core.formatter.insert_space_after_comma_in_constructor_declaration_parameters",
            "insert"
        );
        config.put(
            "org.eclipse.jdt.core.formatter.insert_space_after_comma_in_constructor_declaration_throws",
            "insert"
        );
        config.put("org.eclipse.jdt.core.formatter.insert_space_after_comma_in_enum_constant_arguments", "insert");
        config.put("org.eclipse.jdt.core.formatter.insert_space_after_comma_in_enum_declarations", "insert");
        config.put(
            "org.eclipse.jdt.core.formatter.insert_space_after_comma_in_explicitconstructorcall_arguments",
            "insert"
        );
        config.put("org.eclipse.jdt.core.formatter.insert_space_after_comma_in_for_increments", "insert");
        config.put("org.eclipse.jdt.core.formatter.insert_space_after_comma_in_for_inits", "insert");
        config.put(
            "org.eclipse.jdt.core.formatter.insert_space_after_comma_in_method_declaration_parameters",
            "insert"
        );
        config.put("org.eclipse.jdt.core.formatter.insert_space_after_comma_in_method_declaration_throws", "insert");
        config.put("org.eclipse.jdt.core.formatter.insert_space_after_comma_in_method_invocation_arguments", "insert");
        config.put("org.eclipse.jdt.core.formatter.insert_space_after_comma_in_multiple_field_declarations", "insert");
        config.put("org.eclipse.jdt.core.formatter.insert_space_after_comma_in_multiple_local_declarations", "insert");
        config.put("org.eclipse.jdt.core.formatter.insert_space_after_comma_in_parameterized_type_reference", "insert");
        config.put("org.eclipse.jdt.core.formatter.insert_space_after_comma_in_permitted_types", "insert");
        config.put("org.eclipse.jdt.core.formatter.insert_space_after_comma_in_record_components", "insert");
        config.put("org.eclipse.jdt.core.formatter.insert_space_after_comma_in_superinterfaces", "insert");
        config.put("org.eclipse.jdt.core.formatter.insert_space_after_comma_in_switch_case_expressions", "insert");
        config.put("org.eclipse.jdt.core.formatter.insert_space_after_comma_in_type_arguments", "insert");
        config.put("org.eclipse.jdt.core.formatter.insert_space_after_comma_in_type_parameters", "insert");
        config.put("org.eclipse.jdt.core.formatter.insert_space_after_ellipsis", "insert");
        config.put("org.eclipse.jdt.core.formatter.insert_space_after_lambda_arrow", "insert");
        config.put("org.eclipse.jdt.core.formatter.insert_space_after_logical_operator", "insert");
        config.put("org.eclipse.jdt.core.formatter.insert_space_after_multiplicative_operator", "insert");
        config.put("org.eclipse.jdt.core.formatter.insert_space_after_not_operator", "do not insert");
        config.put(
            "org.eclipse.jdt.core.formatter.insert_space_after_opening_angle_bracket_in_parameterized_type_reference",
            "do not insert"
        );
        config.put(
            "org.eclipse.jdt.core.formatter.insert_space_after_opening_angle_bracket_in_type_arguments",
            "do not insert"
        );
        config.put(
            "org.eclipse.jdt.core.formatter.insert_space_after_opening_angle_bracket_in_type_parameters",
            "do not insert"
        );
        config.put("org.eclipse.jdt.core.formatter.insert_space_after_opening_brace_in_array_initializer", "insert");
        config.put(
            "org.eclipse.jdt.core.formatter.insert_space_after_opening_bracket_in_array_allocation_expression",
            "do not insert"
        );
        config.put(
            "org.eclipse.jdt.core.formatter.insert_space_after_opening_bracket_in_array_reference",
            "do not insert"
        );
        config.put("org.eclipse.jdt.core.formatter.insert_space_after_opening_paren_in_annotation", "do not insert");
        config.put("org.eclipse.jdt.core.formatter.insert_space_after_opening_paren_in_cast", "do not insert");
        config.put("org.eclipse.jdt.core.formatter.insert_space_after_opening_paren_in_catch", "do not insert");
        config.put(
            "org.eclipse.jdt.core.formatter.insert_space_after_opening_paren_in_constructor_declaration",
            "do not insert"
        );
        config.put("org.eclipse.jdt.core.formatter.insert_space_after_opening_paren_in_enum_constant", "do not insert");
        config.put("org.eclipse.jdt.core.formatter.insert_space_after_opening_paren_in_for", "do not insert");
        config.put("org.eclipse.jdt.core.formatter.insert_space_after_opening_paren_in_if", "do not insert");
        config.put(
            "org.eclipse.jdt.core.formatter.insert_space_after_opening_paren_in_method_declaration",
            "do not insert"
        );
        config.put(
            "org.eclipse.jdt.core.formatter.insert_space_after_opening_paren_in_method_invocation",
            "do not insert"
        );
        config.put(
            "org.eclipse.jdt.core.formatter.insert_space_after_opening_paren_in_parenthesized_expression",
            "do not insert"
        );
        config.put(
            "org.eclipse.jdt.core.formatter.insert_space_after_opening_paren_in_record_declaration",
            "do not insert"
        );
        config.put("org.eclipse.jdt.core.formatter.insert_space_after_opening_paren_in_switch", "do not insert");
        config.put("org.eclipse.jdt.core.formatter.insert_space_after_opening_paren_in_synchronized", "do not insert");
        config.put("org.eclipse.jdt.core.formatter.insert_space_after_opening_paren_in_try", "do not insert");
        config.put("org.eclipse.jdt.core.formatter.insert_space_after_opening_paren_in_while", "do not insert");
        config.put("org.eclipse.jdt.core.formatter.insert_space_after_postfix_operator", "do not insert");
        config.put("org.eclipse.jdt.core.formatter.insert_space_after_prefix_operator", "do not insert");
        config.put("org.eclipse.jdt.core.formatter.insert_space_after_question_in_conditional", "insert");
        config.put("org.eclipse.jdt.core.formatter.insert_space_after_question_in_wildcard", "do not insert");
        config.put("org.eclipse.jdt.core.formatter.insert_space_after_relational_operator", "insert");
        config.put("org.eclipse.jdt.core.formatter.insert_space_after_semicolon_in_for", "insert");
        config.put("org.eclipse.jdt.core.formatter.insert_space_after_semicolon_in_try_resources", "insert");
        config.put("org.eclipse.jdt.core.formatter.insert_space_after_shift_operator", "insert");
        config.put("org.eclipse.jdt.core.formatter.insert_space_after_string_concatenation", "insert");
        config.put("org.eclipse.jdt.core.formatter.insert_space_after_unary_operator", "do not insert");
        config.put("org.eclipse.jdt.core.formatter.insert_space_before_additive_operator", "insert");
        config.put("org.eclipse.jdt.core.formatter.insert_space_before_and_in_type_parameter", "insert");
        config.put("org.eclipse.jdt.core.formatter.insert_space_before_arrow_in_switch_case", "insert");
        config.put("org.eclipse.jdt.core.formatter.insert_space_before_arrow_in_switch_default", "insert");
        config.put("org.eclipse.jdt.core.formatter.insert_space_before_assignment_operator", "insert");
        config.put("org.eclipse.jdt.core.formatter.insert_space_before_at_in_annotation_type_declaration", "insert");
        config.put("org.eclipse.jdt.core.formatter.insert_space_before_bitwise_operator", "insert");
        config.put(
            "org.eclipse.jdt.core.formatter.insert_space_before_closing_angle_bracket_in_parameterized_type_reference",
            "do not insert"
        );
        config.put(
            "org.eclipse.jdt.core.formatter.insert_space_before_closing_angle_bracket_in_type_arguments",
            "do not insert"
        );
        config.put(
            "org.eclipse.jdt.core.formatter.insert_space_before_closing_angle_bracket_in_type_parameters",
            "do not insert"
        );
        config.put("org.eclipse.jdt.core.formatter.insert_space_before_closing_brace_in_array_initializer", "insert");
        config.put(
            "org.eclipse.jdt.core.formatter.insert_space_before_closing_bracket_in_array_allocation_expression",
            "do not insert"
        );
        config.put(
            "org.eclipse.jdt.core.formatter.insert_space_before_closing_bracket_in_array_reference",
            "do not insert"
        );
        config.put("org.eclipse.jdt.core.formatter.insert_space_before_closing_paren_in_annotation", "do not insert");
        config.put("org.eclipse.jdt.core.formatter.insert_space_before_closing_paren_in_cast", "do not insert");
        config.put("org.eclipse.jdt.core.formatter.insert_space_before_closing_paren_in_catch", "do not insert");
        config.put(
            "org.eclipse.jdt.core.formatter.insert_space_before_closing_paren_in_constructor_declaration",
            "do not insert"
        );
        config.put(
            "org.eclipse.jdt.core.formatter.insert_space_before_closing_paren_in_enum_constant",
            "do not insert"
        );
        config.put("org.eclipse.jdt.core.formatter.insert_space_before_closing_paren_in_for", "do not insert");
        config.put("org.eclipse.jdt.core.formatter.insert_space_before_closing_paren_in_if", "do not insert");
        config.put(
            "org.eclipse.jdt.core.formatter.insert_space_before_closing_paren_in_method_declaration",
            "do not insert"
        );
        config.put(
            "org.eclipse.jdt.core.formatter.insert_space_before_closing_paren_in_method_invocation",
            "do not insert"
        );
        config.put(
            "org.eclipse.jdt.core.formatter.insert_space_before_closing_paren_in_parenthesized_expression",
            "do not insert"
        );
        config.put(
            "org.eclipse.jdt.core.formatter.insert_space_before_closing_paren_in_record_declaration",
            "do not insert"
        );
        config.put("org.eclipse.jdt.core.formatter.insert_space_before_closing_paren_in_switch", "do not insert");
        config.put("org.eclipse.jdt.core.formatter.insert_space_before_closing_paren_in_synchronized", "do not insert");
        config.put("org.eclipse.jdt.core.formatter.insert_space_before_closing_paren_in_try", "do not insert");
        config.put("org.eclipse.jdt.core.formatter.insert_space_before_closing_paren_in_while", "do not insert");
        config.put("org.eclipse.jdt.core.formatter.insert_space_before_colon_in_assert", "insert");
        config.put("org.eclipse.jdt.core.formatter.insert_space_before_colon_in_case", "do not insert");
        config.put("org.eclipse.jdt.core.formatter.insert_space_before_colon_in_conditional", "insert");
        config.put("org.eclipse.jdt.core.formatter.insert_space_before_colon_in_default", "do not insert");
        config.put("org.eclipse.jdt.core.formatter.insert_space_before_colon_in_for", "insert");
        config.put("org.eclipse.jdt.core.formatter.insert_space_before_colon_in_labeled_statement", "do not insert");
        config.put(
            "org.eclipse.jdt.core.formatter.insert_space_before_comma_in_allocation_expression",
            "do not insert"
        );
        config.put("org.eclipse.jdt.core.formatter.insert_space_before_comma_in_annotation", "do not insert");
        config.put("org.eclipse.jdt.core.formatter.insert_space_before_comma_in_array_initializer", "do not insert");
        config.put(
            "org.eclipse.jdt.core.formatter.insert_space_before_comma_in_constructor_declaration_parameters",
            "do not insert"
        );
        config.put(
            "org.eclipse.jdt.core.formatter.insert_space_before_comma_in_constructor_declaration_throws",
            "do not insert"
        );
        config.put(
            "org.eclipse.jdt.core.formatter.insert_space_before_comma_in_enum_constant_arguments",
            "do not insert"
        );
        config.put("org.eclipse.jdt.core.formatter.insert_space_before_comma_in_enum_declarations", "do not insert");
        config.put(
            "org.eclipse.jdt.core.formatter.insert_space_before_comma_in_explicitconstructorcall_arguments",
            "do not insert"
        );
        config.put("org.eclipse.jdt.core.formatter.insert_space_before_comma_in_for_increments", "do not insert");
        config.put("org.eclipse.jdt.core.formatter.insert_space_before_comma_in_for_inits", "do not insert");
        config.put(
            "org.eclipse.jdt.core.formatter.insert_space_before_comma_in_method_declaration_parameters",
            "do not insert"
        );
        config.put(
            "org.eclipse.jdt.core.formatter.insert_space_before_comma_in_method_declaration_throws",
            "do not insert"
        );
        config.put(
            "org.eclipse.jdt.core.formatter.insert_space_before_comma_in_method_invocation_arguments",
            "do not insert"
        );
        config.put(
            "org.eclipse.jdt.core.formatter.insert_space_before_comma_in_multiple_field_declarations",
            "do not insert"
        );
        config.put(
            "org.eclipse.jdt.core.formatter.insert_space_before_comma_in_multiple_local_declarations",
            "do not insert"
        );
        config.put(
            "org.eclipse.jdt.core.formatter.insert_space_before_comma_in_parameterized_type_reference",
            "do not insert"
        );
        config.put("org.eclipse.jdt.core.formatter.insert_space_before_comma_in_permitted_types", "do not insert");
        config.put("org.eclipse.jdt.core.formatter.insert_space_before_comma_in_record_components", "do not insert");
        config.put("org.eclipse.jdt.core.formatter.insert_space_before_comma_in_superinterfaces", "do not insert");
        config.put(
            "org.eclipse.jdt.core.formatter.insert_space_before_comma_in_switch_case_expressions",
            "do not insert"
        );
        config.put("org.eclipse.jdt.core.formatter.insert_space_before_comma_in_type_arguments", "do not insert");
        config.put("org.eclipse.jdt.core.formatter.insert_space_before_comma_in_type_parameters", "do not insert");
        config.put("org.eclipse.jdt.core.formatter.insert_space_before_ellipsis", "do not insert");
        config.put("org.eclipse.jdt.core.formatter.insert_space_before_lambda_arrow", "insert");
        config.put("org.eclipse.jdt.core.formatter.insert_space_before_logical_operator", "insert");
        config.put("org.eclipse.jdt.core.formatter.insert_space_before_multiplicative_operator", "insert");
        config.put(
            "org.eclipse.jdt.core.formatter.insert_space_before_opening_angle_bracket_in_parameterized_type_reference",
            "do not insert"
        );
        config.put(
            "org.eclipse.jdt.core.formatter.insert_space_before_opening_angle_bracket_in_type_arguments",
            "do not insert"
        );
        config.put(
            "org.eclipse.jdt.core.formatter.insert_space_before_opening_angle_bracket_in_type_parameters",
            "do not insert"
        );
        config.put(
            "org.eclipse.jdt.core.formatter.insert_space_before_opening_brace_in_annotation_type_declaration",
            "insert"
        );
        config.put(
            "org.eclipse.jdt.core.formatter.insert_space_before_opening_brace_in_anonymous_type_declaration",
            "insert"
        );
        config.put("org.eclipse.jdt.core.formatter.insert_space_before_opening_brace_in_array_initializer", "insert");
        config.put("org.eclipse.jdt.core.formatter.insert_space_before_opening_brace_in_block", "insert");
        config.put(
            "org.eclipse.jdt.core.formatter.insert_space_before_opening_brace_in_constructor_declaration",
            "insert"
        );
        config.put("org.eclipse.jdt.core.formatter.insert_space_before_opening_brace_in_enum_constant", "insert");
        config.put("org.eclipse.jdt.core.formatter.insert_space_before_opening_brace_in_enum_declaration", "insert");
        config.put("org.eclipse.jdt.core.formatter.insert_space_before_opening_brace_in_method_declaration", "insert");
        config.put("org.eclipse.jdt.core.formatter.insert_space_before_opening_brace_in_record_constructor", "insert");
        config.put("org.eclipse.jdt.core.formatter.insert_space_before_opening_brace_in_record_declaration", "insert");
        config.put("org.eclipse.jdt.core.formatter.insert_space_before_opening_brace_in_switch", "insert");
        config.put("org.eclipse.jdt.core.formatter.insert_space_before_opening_brace_in_type_declaration", "insert");
        config.put(
            "org.eclipse.jdt.core.formatter.insert_space_before_opening_bracket_in_array_allocation_expression",
            "do not insert"
        );
        config.put(
            "org.eclipse.jdt.core.formatter.insert_space_before_opening_bracket_in_array_reference",
            "do not insert"
        );
        config.put(
            "org.eclipse.jdt.core.formatter.insert_space_before_opening_bracket_in_array_type_reference",
            "do not insert"
        );
        config.put("org.eclipse.jdt.core.formatter.insert_space_before_opening_paren_in_annotation", "do not insert");
        config.put(
            "org.eclipse.jdt.core.formatter.insert_space_before_opening_paren_in_annotation_type_member_declaration",
            "do not insert"
        );
        config.put("org.eclipse.jdt.core.formatter.insert_space_before_opening_paren_in_catch", "insert");
        config.put(
            "org.eclipse.jdt.core.formatter.insert_space_before_opening_paren_in_constructor_declaration",
            "do not insert"
        );
        config.put(
            "org.eclipse.jdt.core.formatter.insert_space_before_opening_paren_in_enum_constant",
            "do not insert"
        );
        config.put("org.eclipse.jdt.core.formatter.insert_space_before_opening_paren_in_for", "insert");
        config.put("org.eclipse.jdt.core.formatter.insert_space_before_opening_paren_in_if", "insert");
        config.put(
            "org.eclipse.jdt.core.formatter.insert_space_before_opening_paren_in_method_declaration",
            "do not insert"
        );
        config.put(
            "org.eclipse.jdt.core.formatter.insert_space_before_opening_paren_in_method_invocation",
            "do not insert"
        );
        config.put(
            "org.eclipse.jdt.core.formatter.insert_space_before_opening_paren_in_parenthesized_expression",
            "do not insert"
        );
        config.put(
            "org.eclipse.jdt.core.formatter.insert_space_before_opening_paren_in_record_declaration",
            "do not insert"
        );
        config.put("org.eclipse.jdt.core.formatter.insert_space_before_opening_paren_in_switch", "insert");
        config.put("org.eclipse.jdt.core.formatter.insert_space_before_opening_paren_in_synchronized", "insert");
        config.put("org.eclipse.jdt.core.formatter.insert_space_before_opening_paren_in_try", "insert");
        config.put("org.eclipse.jdt.core.formatter.insert_space_before_opening_paren_in_while", "insert");
        config.put("org.eclipse.jdt.core.formatter.insert_space_before_parenthesized_expression_in_return", "insert");
        config.put("org.eclipse.jdt.core.formatter.insert_space_before_parenthesized_expression_in_throw", "insert");
        config.put("org.eclipse.jdt.core.formatter.insert_space_before_postfix_operator", "do not insert");
        config.put("org.eclipse.jdt.core.formatter.insert_space_before_prefix_operator", "do not insert");
        config.put("org.eclipse.jdt.core.formatter.insert_space_before_question_in_conditional", "insert");
        config.put("org.eclipse.jdt.core.formatter.insert_space_before_question_in_wildcard", "do not insert");
        config.put("org.eclipse.jdt.core.formatter.insert_space_before_relational_operator", "insert");
        config.put("org.eclipse.jdt.core.formatter.insert_space_before_semicolon", "do not insert");
        config.put("org.eclipse.jdt.core.formatter.insert_space_before_semicolon_in_for", "do not insert");
        config.put("org.eclipse.jdt.core.formatter.insert_space_before_semicolon_in_try_resources", "do not insert");
        config.put("org.eclipse.jdt.core.formatter.insert_space_before_shift_operator", "insert");
        config.put("org.eclipse.jdt.core.formatter.insert_space_before_string_concatenation", "insert");
        config.put("org.eclipse.jdt.core.formatter.insert_space_before_unary_operator", "do not insert");
        config.put(
            "org.eclipse.jdt.core.formatter.insert_space_between_brackets_in_array_type_reference",
            "do not insert"
        );
        config.put(
            "org.eclipse.jdt.core.formatter.insert_space_between_empty_braces_in_array_initializer",
            "do not insert"
        );
        config.put(
            "org.eclipse.jdt.core.formatter.insert_space_between_empty_brackets_in_array_allocation_expression",
            "do not insert"
        );
        config.put(
            "org.eclipse.jdt.core.formatter.insert_space_between_empty_parens_in_annotation_type_member_declaration",
            "do not insert"
        );
        config.put(
            "org.eclipse.jdt.core.formatter.insert_space_between_empty_parens_in_constructor_declaration",
            "do not insert"
        );
        config.put(
            "org.eclipse.jdt.core.formatter.insert_space_between_empty_parens_in_enum_constant",
            "do not insert"
        );
        config.put(
            "org.eclipse.jdt.core.formatter.insert_space_between_empty_parens_in_method_declaration",
            "do not insert"
        );
        config.put(
            "org.eclipse.jdt.core.formatter.insert_space_between_empty_parens_in_method_invocation",
            "do not insert"
        );
        config.put("org.eclipse.jdt.core.formatter.join_lines_in_comments", "true");
        config.put("org.eclipse.jdt.core.formatter.join_wrapped_lines", "false");
        config.put("org.eclipse.jdt.core.formatter.keep_annotation_declaration_on_one_line", "one_line_if_empty");
        config.put("org.eclipse.jdt.core.formatter.keep_anonymous_type_declaration_on_one_line", "one_line_if_empty");
        config.put("org.eclipse.jdt.core.formatter.keep_code_block_on_one_line", "one_line_if_empty");
        config.put("org.eclipse.jdt.core.formatter.keep_else_statement_on_same_line", "false");
        config.put("org.eclipse.jdt.core.formatter.keep_empty_array_initializer_on_one_line", "true");
        config.put("org.eclipse.jdt.core.formatter.keep_enum_constant_declaration_on_one_line", "one_line_if_empty");
        config.put("org.eclipse.jdt.core.formatter.keep_enum_declaration_on_one_line", "one_line_if_empty");
        config.put("org.eclipse.jdt.core.formatter.keep_if_then_body_block_on_one_line", "one_line_if_empty");
        config.put("org.eclipse.jdt.core.formatter.keep_imple_if_on_one_line", "false");
        config.put("org.eclipse.jdt.core.formatter.keep_lambda_body_block_on_one_line", "one_line_if_empty");
        config.put("org.eclipse.jdt.core.formatter.keep_loop_body_block_on_one_line", "one_line_if_empty");
        config.put("org.eclipse.jdt.core.formatter.keep_method_body_on_one_line", "one_line_if_empty");
        config.put("org.eclipse.jdt.core.formatter.keep_record_constructor_on_one_line", "one_line_if_empty");
        config.put("org.eclipse.jdt.core.formatter.keep_record_declaration_on_one_line", "one_line_if_empty");
        config.put("org.eclipse.jdt.core.formatter.keep_simple_do_while_body_on_same_line", "true");
        config.put("org.eclipse.jdt.core.formatter.keep_simple_for_body_on_same_line", "true");
        config.put("org.eclipse.jdt.core.formatter.keep_simple_getter_setter_on_one_line", "false");
        config.put("org.eclipse.jdt.core.formatter.keep_simple_while_body_on_same_line", "true");
        config.put("org.eclipse.jdt.core.formatter.keep_switch_body_block_on_one_line", "one_line_if_empty");
        config.put("org.eclipse.jdt.core.formatter.keep_switch_case_with_arrow_on_one_line", "one_line_if_single_item");
        config.put("org.eclipse.jdt.core.formatter.keep_then_statement_on_same_line", "false");
        config.put("org.eclipse.jdt.core.formatter.keep_type_declaration_on_one_line", "one_line_if_empty");
        config.put("org.eclipse.jdt.core.formatter.lineSplit", "120");
        config.put("org.eclipse.jdt.core.formatter.never_indent_block_comments_on_first_column", "false");
        config.put("org.eclipse.jdt.core.formatter.never_indent_line_comments_on_first_column", "false");
        config.put("org.eclipse.jdt.core.formatter.number_of_blank_lines_after_code_block", "0");
        config.put("org.eclipse.jdt.core.formatter.number_of_blank_lines_at_beginning_of_code_block", "0");
        config.put("org.eclipse.jdt.core.formatter.number_of_blank_lines_at_beginning_of_method_body", "0");
        config.put("org.eclipse.jdt.core.formatter.number_of_blank_lines_at_end_of_code_block", "0");
        config.put("org.eclipse.jdt.core.formatter.number_of_blank_lines_at_end_of_method_body", "0");
        config.put("org.eclipse.jdt.core.formatter.number_of_blank_lines_before_code_block", "0");
        config.put("org.eclipse.jdt.core.formatter.number_of_empty_lines_to_preserve", "1");
        config.put("org.eclipse.jdt.core.formatter.parentheses_positions_in_annotation", "separate_lines_if_wrapped");
        config.put("org.eclipse.jdt.core.formatter.parentheses_positions_in_catch_clause", "separate_lines_if_wrapped");
        config.put(
            "org.eclipse.jdt.core.formatter.parentheses_positions_in_enum_constant_declaration",
            "separate_lines_if_wrapped"
        );
        config.put("org.eclipse.jdt.core.formatter.parentheses_positions_in_for_statment", "separate_lines_if_wrapped");
        config.put(
            "org.eclipse.jdt.core.formatter.parentheses_positions_in_if_while_statement",
            "separate_lines_if_wrapped"
        );
        config.put(
            "org.eclipse.jdt.core.formatter.parentheses_positions_in_lambda_declaration",
            "separate_lines_if_wrapped"
        );
        config.put(
            "org.eclipse.jdt.core.formatter.parentheses_positions_in_method_delcaration",
            "separate_lines_if_wrapped"
        );
        config.put(
            "org.eclipse.jdt.core.formatter.parentheses_positions_in_method_invocation",
            "separate_lines_if_wrapped"
        );
        config.put(
            "org.eclipse.jdt.core.formatter.parentheses_positions_in_record_declaration",
            "separate_lines_if_wrapped"
        );
        config.put(
            "org.eclipse.jdt.core.formatter.parentheses_positions_in_switch_statement",
            "separate_lines_if_wrapped"
        );
        config.put("org.eclipse.jdt.core.formatter.parentheses_positions_in_try_clause", "separate_lines_if_wrapped");
        config.put("org.eclipse.jdt.core.formatter.put_empty_statement_on_new_line", "true");
        config.put("org.eclipse.jdt.core.formatter.tabulation.char", "space");
        config.put("org.eclipse.jdt.core.formatter.tabulation.size", "4");
        config.put("org.eclipse.jdt.core.formatter.text_block_indentation", "0");
        config.put("org.eclipse.jdt.core.formatter.use_on_off_tags", "true");
        config.put("org.eclipse.jdt.core.formatter.use_tabs_only_for_leading_indentations", "false");
        config.put("org.eclipse.jdt.core.formatter.wrap_before_additive_operator", "false");
        config.put("org.eclipse.jdt.core.formatter.wrap_before_assertion_message_operator", "true");
        config.put("org.eclipse.jdt.core.formatter.wrap_before_assignment_operator", "false");
        config.put("org.eclipse.jdt.core.formatter.wrap_before_bitwise_operator", "false");
        config.put("org.eclipse.jdt.core.formatter.wrap_before_conditional_operator", "true");
        config.put("org.eclipse.jdt.core.formatter.wrap_before_logical_operator", "false");
        config.put("org.eclipse.jdt.core.formatter.wrap_before_multiplicative_operator", "false");
        config.put("org.eclipse.jdt.core.formatter.wrap_before_or_operator_multicatch", "true");
        config.put("org.eclipse.jdt.core.formatter.wrap_before_relational_operator", "true");
        config.put("org.eclipse.jdt.core.formatter.wrap_before_shift_operator", "true");
        config.put("org.eclipse.jdt.core.formatter.wrap_before_string_concatenation", "false");
        config.put("org.eclipse.jdt.core.formatter.wrap_before_switch_case_arrow_operator", "false");
        config.put("org.eclipse.jdt.core.formatter.wrap_outer_expressions_when_nested", "false");
        CONFIG = Collections.unmodifiableMap(config);
    };
}
